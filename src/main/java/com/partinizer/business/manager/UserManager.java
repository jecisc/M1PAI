package com.partinizer.business.manager;

import com.partinizer.data.entity.User;
import com.partinizer.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by vincent on 10/03/16.
 */
@Component
public class UserManager {

    private String regexPassword = "^(?=.*[0-9#\\$~<>\\|&-/])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,40}$";
    private String regexMail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private UserRepository userRepository;

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
                    this.sendAcceptionMailTo(user);
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

        User user= userRepository.getFriendsById(id);


        return user;


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

        List<User> userList=userRepository.findByPseudoStartingWith(pseudo);

        return userList;
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

        return password != null && password.matches(regexPassword);
    }

    private boolean checkMail(String mail) {

        return mail != null && !mail.equals("") && mail.matches(regexMail);
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

    public void sendAcceptionMailTo(User aUser) throws MessagingException {
        //TODO remove hard code

        Integer port = 465;
        String host = "smtp.gmail.com";
        String from = "m1paiemail@gmail.com";
        String username = "m1paiemail@gmail.com";
        String password = "cotelette";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.ssl.enable", true);
        props.put("mail.smtp.auth", true);

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication pa = new PasswordAuthentication(username, password);

            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return pa;
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        InternetAddress[] address = {new InternetAddress(aUser.getMail())};
        message.setRecipients(Message.RecipientType.TO, address);
        message.setSubject("Partinizer - Inscription");
        message.setSentDate(new Date());
        message.setText("Bonjour,\n" +
                "\n" +
                "Vous recevez cet email car quelqu'un, avec un petit peu de chance vous, s'est inscrit à Partinizer.\n"+
                "S'il s'agit de vous, cliquez juste sur le lien ci dessous ou copiez/collez le dans votre navigateur.\n" +
                "\n" +
                "https://localhost:8080/user/validation?" + aUser.getMail().toString() + "&" + aUser.getMail().hashCode() + "\n" +
                "\n" +
                "Si vous n'avez pas demandé cette inscription, veuillez ignorer cet email..\n" +
                "Merci,\n" +
                "Partinizer ");
        Transport.send(message);


    }

    public User getUserByMail(String mail) {
        return this.userRepository.findByMail(mail);
    }
}
