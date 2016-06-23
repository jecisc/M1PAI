package com.partinizer.business.service;

import com.partinizer.business.exceptions.UserDoesNotExistException;
import com.partinizer.business.exceptions.WrongInformationException;
import com.partinizer.business.manager.UserManager;
import com.partinizer.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import java.util.List;

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

    /**
     * I return the number of result on searching users filter by pseudo
     * @param pseudoFilter The filter of search
     * @return The number of result
     */
    public int getNumberOfUsersFilterByPseudo(String pseudoFilter){
        return userManager.getNumberOfUsersFilterByPseudo(pseudoFilter);
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

    public List<User> searchUser(User user,String pseudo,int page, int size){
        List<User> resultList=userManager.searchUser(user,pseudo,page,size);
        List<User> friendsAskingList=userManager.getFriendsAsking(user);
        resultList.removeAll(friendsAskingList);
        return resultList;
    }

    public List<User> getFriendsAsking(User user){
        return userManager.getFriendsAsking(user);
    }

    public boolean deleteFriend(User user, long idFriend){
        return userManager.deleteFriend(user,idFriend);
    }

    public boolean addFriend(User user,long idFriend) {return userManager.addFriend(user,idFriend);}

    public boolean denyFriendRequest(User user,long idFriend){return userManager.deleteFriendRequest(user,idFriend);}

    //TODO catch exception
    public boolean addFriendRequest(User user,long idFriend){
        return userManager.addFriendRequest(user,idFriend);
    }

    public User updateUser(User userUpdate,User userAuthenticate) throws WrongInformationException {
        return userManager.updateUser(userUpdate,userAuthenticate);
    }

}
