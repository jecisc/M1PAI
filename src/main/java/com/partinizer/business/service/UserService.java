package com.partinizer.business.service;

import com.partinizer.business.manager.UserManager;
import com.partinizer.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        user=userManager.createUser(user);

        return user;
    }

    public User getUserByMailOrPseudo(User user){
        user=userManager.getUserByMailOrPseudo(user);

        return user;
    }

    public User getAllFriends(long id){
        User user= userManager.getAllFriends(id);
        return user;
    }

    public User getUserById(User user){
        user=userManager.getUser(user);

        return user;
    }
}
