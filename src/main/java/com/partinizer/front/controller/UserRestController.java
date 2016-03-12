package com.partinizer.front.controller;

import com.partinizer.business.manager.UserManager;
import com.partinizer.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
 * Created by vincent on 10/03/16.
 */
@RestController
@RequestMapping("/")
public class UserRestController {


    private UserManager userManager;

    @Autowired
    public UserRestController(UserManager userManager){
        this.userManager=userManager;
    }

    @RequestMapping(value="/{user}", method= RequestMethod.GET)
    public User userTest(
            @PathVariable("user") String user_){

        System.out.println(user_);

        User user= new User();
        user.setName("vincent");
        user.setMail("vincentmargerin@gmail.com");
        user.setPassword("password");
        user.setRegistDate(new Date(System.currentTimeMillis()));
        user.setActive(true);
        user.setPseudo("jalzuritro");
        user.setAvatar("");

        // userManager.createUser(user);*/

        return user;
    }
}
