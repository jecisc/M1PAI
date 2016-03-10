package com.partinizer.front.controller;

import com.partinizer.business.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



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
    public void userTest(
            @PathVariable("user") String user){


        System.out.println("TEST");
    }
}
