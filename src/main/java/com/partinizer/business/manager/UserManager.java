package com.partinizer.business.manager;

import com.partinizer.data.entity.User;
import com.partinizer.data.repository.UserRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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
    protected final static  String REGEXMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
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
     * Create a User entity if all require fields are fill
     *
     * @param user
     * @return The User created or null if not
     */
    public User createUser(User user) {

        if (checkPseudo(user.getPseudo()) && checkPassword(user.getPassword())
                && checkMail(user.getMail()) && checkName(user.getName()) &&
                checkFirstName(user.getFirstName()) && checkActive(user.isActive())) {
            /* Check if mail or pseudo already exist */
            User user_exist = getUserByMailOrPseudo(user);

            if (user_exist == null) {
                user.setRegistDate(new Date(System.currentTimeMillis()));
                try {
                    this.sendMailTo(user, "Partinizer - Inscription", this.subscriptionMailContentFor(user));
                } catch (MessagingException e) {
                    e.printStackTrace(); //TODO maybe a retry?
                }
                return userRepository.save(user);

            }
        }

        return null;
    }


    /**
     * Get a User entity by his id
     *
     * @param user
     * @return The User obtained or null if not
     */
    public User getUser(User user) {
        if (validUser(user)) {

            return userRepository.findOne(user.getId());

        }

        return null;
    }

    public User getAllFriends(long id){
        return userRepository.getFriendsById(id);
    }

    /**
     * Get a User entity by his mail or his pseudo
     *
     * @param user
     * @return The user obtained or null if not
     */
    public User getUserByMailOrPseudo(User user) {

        if (user != null && (user.getMail() != null && !user.getMail().equals("")) ||
                (user.getPseudo() != null && !user.getPseudo().equals(""))) {
            return userRepository.findByMailOrPseudo(user.getMail(), user.getPseudo());

        }

        return null;
    }

    public Boolean validateUserSubscription(User user) {
        user.setActive(true);
        return (this.userRepository.save(user)).isActive();
    }

    public List<User> findByPseudo(String pseudo){
        return userRepository.findByPseudoStartingWith(pseudo);
    }


    /**
     * Delete a User by his Id
     *
     * @param user
     * @return The User deleted or null if not
     */
    public User deleteUser(User user) {

        if (validUser(user) && userExist(user)) {
            userRepository.delete(user);

            return user;
        }

        return null;
    }


    private boolean validUser(User user) {

        return user != null && user.getId() != 0;
    }


    private boolean userExist(User user) {

        return getUser(user) != null;
    }

    private boolean checkPseudo(String pseudo) {

        return pseudo != null && pseudo.length() > 4 && pseudo.length() < 20;

    }

    private boolean checkPassword(String password) {

        return password != null && password.matches(UserManager.REGEXPASSWORD);
    }

    private boolean checkMail(String mail) {

        return mail != null && !mail.equals("") && mail.matches(UserManager.REGEXMAIL);
    }

    private boolean checkName(String name) {
        return name != null && !name.equals("") && name.length() >= 3 && name.length() <= 40;
    }

    private boolean checkFirstName(String firstName) {
        return firstName != null && !firstName.equals("") && firstName.length() >= 3 && firstName.length() <= 40;
    }

    private boolean checkActive(Boolean active) {
        return !active;
    }

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

    public String subscriptionMailContentFor(User aUser) {
        return "Bonjour,\n" +
                "\n" +
                "Vous recevez cet email car quelqu'un, avec un petit peu de chance vous, s'est inscrit à Partinizer.\n"+
                "S'il s'agit de vous, cliquez juste sur le lien ci dessous ou copiez/collez le dans votre navigateur.\n" +
                "\n" +
                "http://localhost:8080/user/validation?mail=" + aUser.getMail().toString() + "&cle=" + aUser.getMail().hashCode() + "\n" +
                "\n" +
                "Si vous n'avez pas demandé cette inscription, veuillez ignorer cet email..\n" +
                "Merci,\n" +
                "Partinizer ";
    }

    public User getUserByMail(String mail) {
        return this.userRepository.findByMail(mail);
    }

    public User getUserByPseudo(String pseudo) {
        return this.userRepository.findByPseudo(pseudo);
    }

    public String generateNewPassword() {
        return RandomStringUtils.random(8, 0, 0, true, true, null, new SecureRandom());
    }

    public boolean generateNewPasswordFor(User user) {
        try {
            String newPW = this.generateNewPassword();
            this.sendMailTo(user, "Partinizer - Nouveau Mot de Passe", this.resetPasswordMailContentFor(user, newPW));
            user.setPassword(newPW);
            return (userRepository.save(user).getPassword().equals(newPW));
        } catch (MessagingException e) {
           return false;
        }
    }

    private String resetPasswordMailContentFor(User user, String passwd) {
        return "Bonjour,\n" +
                "\n" +
                "Vous recevez cet email car vous avez demandé un nouveau mot de passe.\n"+
                "Voici un nouveau mot de passe temporaire: "+ passwd + ".\n" +
                "\n" +
                "Si vous n'avez pas demandé ce nouveau mot de passe, veuillez nous contacter.\n" +
                "Merci,\n" +
                "Partinizer ";
    }
}
