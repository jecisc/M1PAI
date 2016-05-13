package com.partinizer.business.manager;

import com.partinizer.business.exceptions.*;
import com.partinizer.data.entity.User;
import com.partinizer.data.repository.UserRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by vincent on 10/03/16.
 */
@Component
public class UserManager {

    protected final static String REGEXPASSWORD = "^(?=.*[0-9#\\$~<>\\|&-/])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,40}$";
    protected final static String REGEXMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    protected static final Integer PORT = 465;
    protected static final String HOST = "smtp.gmail.com";
    protected static final String FROM = "m1paiemail@gmail.com";
    protected static final String USERNAME = "m1paiemail@gmail.com";
    protected static final String PASSWORD = "cotelette";
    protected UserRepository userRepository;

    @Autowired
    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //TODO limiter le nombre de caractère  password, confirmation password

    /**
     * I add a new user in the database and send an email to activate the account.
     *
     * @param user The user to add.
     * @return The user updated (for example with a new id).
     * @throws WrongInformationException raised of a user have a wrong information.
     */
    public User createUser(User user) throws WrongInformationException {

        this.userIsValidForSubscription(user);
        user.setRegistDate(new Date(System.currentTimeMillis()));

        try {
            this.sendMailTo(user, "Partinizer - Inscription", this.subscriptionMailContentFor(user));
        } catch (MessagingException e) {
            e.printStackTrace(); //TODO maybe a retry?
        }
        return userRepository.save(user);

    }

    /**
     * I test a user and check that it is a valid user for subscription.
     *
     * @param user The user to check.
     * @throws WrongInformationException raised if the user is not valid.'
     */
    public void userIsValidForSubscription(User user) throws WrongInformationException {
        this.isValidPseudo(user.getPseudo());
        this.isValidPassword(user.getPassword());
        this.isValidMail(user.getMail());
        this.checkName(user.getName());
        this.checkFirstName(user.getFirstName());
        this.checkIsNotActive(user.isActive());
        this.userPseudoIsFree(user.getPseudo());
        this.userMailIsFree(user.getMail());
    }

    public User getAllFriends(long id) {
        return userRepository.getFriendsById(id);
    }


    public List<User> searchUser(String name, int page, int size) {
        return userRepository.findByNameStartingWithOrderByName(name, new PageRequest(page, size));
    }

    /**
     * I change the status of a user from inactive to active.
     *
     * @param user The user to activate.
     * @return A boolean to know if everything was fine.
     */
    public Boolean validateUserSubscription(User user) {
        user.setActive(true);
        return (this.userRepository.save(user)).isActive();
    }

    /**
     * Check updated fields and send a update request to userRepository
     *
     * @param userUpdate
     * @param userAuthenticate
     * @return The user updated
     */
    public User updateUser(User userUpdate, User userAuthenticate) throws WrongInformationException {
        checkFirstName(userUpdate.getFirstName());
        checkName(userUpdate.getName());
        checkAvatar(userUpdate.getAvatar());

        if(userUpdate.getPassword()!=null) {
            isValidPassword(userUpdate.getPassword());
            userAuthenticate.setPassword(userUpdate.getPassword());
        }

        userAuthenticate.setName(userUpdate.getName());
        userAuthenticate.setFirstName(userUpdate.getFirstName());
        userAuthenticate.setAvatar(userUpdate.getAvatar());
        userAuthenticate = userRepository.save(userAuthenticate);
        return userAuthenticate;
    }

    /**
     * Delete a "friend link" between two Users
     *
     * @param user     Current user
     * @param idFriend User friend id
     * @return true if delete works, false if not
     */
    @Transactional
    public boolean deleteFriend(User user, long idFriend) {

        if (validUser(user)) {
            //Check if friend user exist
            User friend = userRepository.getOne(idFriend);
            if (friend != null) {
                userRepository.deleteFriend(user.getId(), idFriend);
                userRepository.deleteFriend(idFriend, user.getId());
                return true;
            }
        }


        return false;
    }

    @Transactional
    public Boolean addFriend(User user, long idFriend) {

        //Check if friend user exist
        User friend = userRepository.getOne(idFriend);
        //Check if friendRequest exist
        if (friend != null && checkFriendRequestExist(user, idFriend)) {
            userRepository.addFriend(user.getId(), idFriend);
            userRepository.addFriend(idFriend, user.getId());
            userRepository.deleteFriendRequest(user.getId(), idFriend);
            return true;
        }

        return false;
    }

    public Boolean deleteFriendRequest(User user, long idFriend) {

        User friend = userRepository.getOne(idFriend);
        //Check if friendRequest exist
        if (friend != null && checkFriendRequestExist(user, idFriend)) {
            userRepository.deleteFriendRequest(user.getId(), idFriend);
            return true;
        }
        return false;
    }

    private boolean validUser(User user) {

        return user != null && user.getId() != 0;
    }

    /**
     * I check that the user pseudo is valid.
     *
     * @param pseudo The pseudo of the user.
     * @throws WrongPseudoException raised if the information is not valid.
     */
    public void isValidPseudo(String pseudo) throws WrongPseudoException {
        if (pseudo == null || pseudo.length() < 4 || pseudo.length() > 20) {
            throw new WrongPseudoException();
        }
    }

    /**
     * I check that the user password is valid.
     *
     * @param password The password of the user.
     * @throws WrongPasswordException raised if the information is not valid.
     */
    public void isValidPassword(String password) throws WrongPasswordException {
        if (password == null || !password.matches(UserManager.REGEXPASSWORD)) {
            throw new WrongPasswordException();
        }
    }

    /**
     * I check that the user mail is valid.
     *
     * @param mail The mail of the user.
     * @throws WrongMailException raised if the information is not valid.
     */
    public void isValidMail(String mail) throws WrongMailException {
        if (mail == null || !mail.matches(UserManager.REGEXMAIL)) {
            throw new WrongMailException();
        }
    }

    /**
     * I check that the user name is valid.
     *
     * @param name The name of the user.
     * @throws WrongNameException raised if the information is not valid.
     */
    public void checkName(String name) throws WrongNameException {
        if (name == null || name.length() < 3 || name.length() > 40) {
            throw new WrongNameException();
        }
    }

    /**
     * I check that the user first name is valid.
     *
     * @param firstName The first name of the user.
     * @throws WrongFirstNameException raised if the information is not valid.
     */
    public void checkFirstName(String firstName) throws WrongFirstNameException {
        if (firstName == null || firstName.length() < 3 || firstName.length() > 40) {
            throw new WrongFirstNameException();
        }
    }

    /**
     * I check that the user activity is valid.
     *
     * @param active The activity of the user.
     * @throws WrongActivityException raised if the information is not valid.
     */
    public void checkIsNotActive(Boolean active) throws WrongActivityException {
        if (active) {
            throw new WrongActivityException();
        }
    }

    public void checkAvatar(String avatar) {
        //return avatar!=null;
        //TODO
    }

    /**
     * I check that the user pseudo is free.
     *
     * @param pseudo The pseudo of the user.
     * @throws WrongPseudoIsNotFreeException raised if the information is not valid.
     */
    public void userPseudoIsFree(String pseudo) throws WrongPseudoIsNotFreeException {
        if (this.userRepository.findByPseudo(pseudo) != null) {
            throw new WrongPseudoIsNotFreeException();
        }
    }

    /**
     * I check that the user mail is free.
     *
     * @param mail The mail of the user.
     * @throws WrongMailIsNotFreeException raised if the information is not valid.
     */
    public void userMailIsFree(String mail) throws WrongMailIsNotFreeException {
        if (this.userRepository.findByMail(mail) != null) {
            throw new WrongMailIsNotFreeException();
        }
    }

    /**
     * I send an email to a user.
     *
     * @param aUser   The user that need to receive the mail.
     * @param title   the title of the mail.
     * @param content The content of the mail.
     * @throws MessagingException raised if there is a problem.
     */
    public void sendMailTo(User aUser, String title, String content) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", UserManager.HOST);
        props.put("mail.smtp.port", UserManager.PORT);
        props.put("mail.smtp.ssl.enable", true);
        props.put("mail.smtp.auth", true);

        String username = UserManager.USERNAME;
        String password = UserManager.PASSWORD;

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication pa = new PasswordAuthentication(username, password);

            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return pa;
            }
        });

        Transport.send(generateMessageFrom(aUser, session, title, content));
    }

    /**
     * I generate a MimeMessage to be send in a mail.
     *
     * @param aUser   The user that need the mail.
     * @param session The session.
     * @param title   The title of the mail.
     * @param content The content of the mail.
     * @return The mime message created.
     * @throws MessagingException raised if there is a problem.
     */
    public MimeMessage generateMessageFrom(User aUser, Session session, String title, String content) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(UserManager.FROM));
        InternetAddress[] address = {new InternetAddress(aUser.getMail())};
        message.setRecipients(Message.RecipientType.TO, address);
        message.setSubject(title);
        message.setSentDate(new Date());
        message.setText(content);
        return message;
    }

    /**
     * I generate a mail for a subscription.
     * This mail will contain a link to activate his account.
     *
     * @param aUser The user that subscribe.
     * @return The content of the mail.
     */
    public String subscriptionMailContentFor(User aUser) {
        return "Bonjour,\n" +
                "\n" +
                "Vous recevez cet email car quelqu'un, avec un petit peu de chance vous, s'est inscrit à Partinizer.\n" +
                "S'il s'agit de vous, cliquez juste sur le lien ci dessous ou copiez/collez le dans votre navigateur.\n" +
                "\n" +
                "http://localhost:8080/user/validation?mail=" + aUser.getMail().toString() + "&cle=" + aUser.getMail().hashCode() + "\n" +
                "\n" +
                "Si vous n'avez pas demandé cette inscription, veuillez ignorer cet email..\n" +
                "Merci,\n" +
                "Partinizer ";
    }

    /**
     * I return the user matching an email.
     *
     * @param mail The email of the user.
     * @return The user matching the mail.
     * @throws UserDoesNotExistException raised if there is no user with this mail.
     */
    public User getUserByMail(String mail) throws UserDoesNotExistException {
        User user = this.userRepository.findByMail(mail);
        if (user == null) {
            UserDoesNotExistException exception = new UserDoesNotExistException();
            exception.setMail(mail);
            throw exception;
        }
        return user;
    }

    /**
     * I return the user matching a pseudo.
     *
     * @param pseudo The pseudo of the user.
     * @return The user matching the pseudo.
     * @throws UserDoesNotExistException raised of there is no user wth this pseudo.
     */
    public User getUserByPseudo(String pseudo) throws UserDoesNotExistException {
        User user = this.userRepository.findByPseudo(pseudo);
        if (user == null) {
            UserDoesNotExistException exception = new UserDoesNotExistException();
            exception.setPseudo(pseudo);
            throw exception;
        }
        return user;
    }

    /**
     * I return a new password for a user.
     *
     * @return the new password.
     */
    public String generateNewPassword() {
        try {
            String password = RandomStringUtils.random(9, 0, 0, true, true, null, new SecureRandom());
            this.isValidPassword(password);
            return password;
        } catch (WrongPasswordException e) {
            //TODO this can be improved but it will do the trick for now.
            return this.generateNewPassword();
        }
    }

    /**
     * I generate a new password for a user and send notify this user.
     *
     * @param user The user that need a new password.
     * @return A boolean to know if everything went fine.
     * @throws MessagingException raised if there is a problem with the mail.
     */
    public Boolean generateNewPasswordFor(User user) throws MessagingException {
        String newPW = this.generateNewPassword();
        this.sendMailTo(user, "Partinizer - Nouveau Mot de Passe", this.resetPasswordMailContentFor(newPW));
        user.setPassword(newPW);
        return (userRepository.save(user).getPassword().equals(newPW));

    }

    /**
     * I generate a mail to send to a user needed a new password.
     *
     * @param passwd The new password.
     * @return The content of the mail.
     */
    public String resetPasswordMailContentFor(String passwd) {
        return "Bonjour,\n" +
                "\n" +
                "Vous recevez cet email car vous avez demandé un nouveau mot de passe.\n" +
                "Voici un nouveau mot de passe temporaire: " + passwd + ".\n" +
                "\n" +
                "Si vous n'avez pas demandé ce nouveau mot de passe, veuillez nous contacter.\n" +
                "Merci,\n" +
                "Partinizer ";
    }

    /**
     * Check if the friendRequest among the list
     *
     * @param user
     * @param idFriend
     * @return true if exist, false if not
     */
    public Boolean checkFriendRequestExist(User user, long idFriend) {
        for (User friendRequest : user.getFriendRequest()) {
            if (friendRequest.getId() == idFriend) {
                return true;
            }
        }

        return false;
    }
}
