package com.partinizer.business.service;

import com.partinizer.business.manager.UserManager;
import com.partinizer.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public User getUserByMail(String mail){
        return userManager.getUserByMail(mail);
    }

    public Boolean validateUserSubscription(User user){
        return this.userManager.validateUserSubscription(user);
    }
}
