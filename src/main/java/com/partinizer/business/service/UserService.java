package com.partinizer.business.service;

import com.partinizer.business.manager.UserManager;
import com.partinizer.data.entity.User;
import jersey.repackaged.com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        this.userManager=userManager;

    }

    public User createUser(User user){

         return userManager.createUser(user);
        
    }

    public User getUserByMailOrPseudo(User user){

        return userManager.getUserByMailOrPseudo(user);

    }

    public User getAllFriends(long id){
        User user= userManager.getAllFriends(id);
        return user;
    }

    public List<User> searchUser(String name,int page, int size){
        List<User> users= userManager.searchUser(name,page,size);
        return users;
    }

    public boolean deleteFriend(User user, long idFriend){
        return userManager.deleteFriend(user,idFriend);
    }

    public boolean addFriend(User user,long idFriend) {return userManager.addFriend(user,idFriend);}

    public boolean denyFriendRequest(User user,long idFriend){return userManager.deleteFriendRequest(user,idFriend);}

    public User updateUser(User userUpdate,User userAuthenticate){
        return userManager.updateUser(userUpdate,userAuthenticate);
    }

    public User getUserByMail(String mail){
        return userManager.getUserByMail(mail);
    }

    public Boolean validateUserSubscription(User user){
        return this.userManager.validateUserSubscription(user);
    }
}
