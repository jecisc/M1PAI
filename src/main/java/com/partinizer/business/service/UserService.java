package com.partinizer.business.service;

import com.partinizer.business.exceptions.UserDoesNotExistException;
import com.partinizer.business.exceptions.WrongInformationException;
import com.partinizer.business.manager.UserManager;
import com.partinizer.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

/**
 * Created by vincent on 12/03/16.
 */

/**
 * Implémentation de la logique métier de l'entité User
 */
@Service
public class UserService {

    UserManager userManager;

    @Autowired
    public UserService(UserManager userManager){
        this.userManager = userManager;
    }

    /**
     * I add a new user in the database.
     * @param user The user to add.
     * @return The user updated (for example with a new id).
     * @throws WrongInformationException raised of a user have a wrong information.
     */
    public User createUser(User user) throws WrongInformationException {
         return userManager.createUser(user);
    }

    public User getUserByMailOrPseudo(User user){
        //TODO
        // This should be remove later.
        // Getting something by pseudo OR mail is a bad idea because we should know what information we have.
        // And more, this return null if there is no user.
        // Null management is bad and should be replace by errors or null object pattern.
        return userManager.getUserByMailOrPseudo(user);

    }

    public User getAllFriends(long id){
        return userManager.getAllFriends(id);
    }

    /**
     * I return the user matching an email.
     * @param mail The email of the user.
     * @return The user matching the mail.
     * @throws UserDoesNotExistException raised if there is no user with this mail.
     */
    public User getUserByMail(String mail) throws UserDoesNotExistException {
        return userManager.getUserByMail(mail);
    }

    /**
     * I return the user matching a pseudo.
     * @param pseudo The pseudo of the user.
     * @return The user matching the pseudo.
     * @throws UserDoesNotExistException raised of there is no user wth this pseudo.
     */
    public User getUserByPseudo(String pseudo) throws UserDoesNotExistException {
        return userManager.getUserByPseudo(pseudo);
    }

    /**
     * I change the status of a user from inactive to active.
     * @param user The user to activate.
     * @return A boolean to know if everything was fine.
     */
    public Boolean validateUserSubscription(User user){
        // TODO raise an error if the user is already active?
        return this.userManager.validateUserSubscription(user);
    }

    /**
     * I generate a new password for a user and send notify this user.
     * @param user The user that need a new password.
     * @return A boolean to know if everything went fine.
     * @throws MessagingException raised if there is a problem with the mail.
     */
    public boolean generateNewPasswordFor(User user) throws MessagingException {
        return this.userManager.generateNewPasswordFor(user);
    }
}
