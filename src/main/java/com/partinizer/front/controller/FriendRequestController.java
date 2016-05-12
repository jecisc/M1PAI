package com.partinizer.front.controller;

import com.partinizer.business.service.UserService;
import com.partinizer.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by vincent on 02/05/2016.
 */
@RestController
@RequestMapping("/friendRequest")
public class FriendRequestController {

    private UserService userService;

    @Autowired
    public FriendRequestController(UserService userService){
        this.userService=userService;
    }


    @RequestMapping(value = "/accept/{idFriend}", method= RequestMethod.GET)
    public ResponseEntity<String> accept(Authentication authentication,@PathVariable("idFriend") long idFriend){
        User user=getUserFromAuthentication(authentication);

        if(userService.addFriend(user,idFriend)){
            return  new ResponseEntity<String>("", HttpStatus.OK);
        }
        return   new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/deny/{idFriend}", method= RequestMethod.GET)
    public ResponseEntity<String> deny(Authentication authentication,@PathVariable("idFriend") long idFriend){
        User user=getUserFromAuthentication(authentication);

        if(userService.denyFriendRequest(user,idFriend)){
            return  new ResponseEntity<String>("", HttpStatus.OK);
        }
        return   new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
    }

    /**
     * Get friend requests
     * @param authentication
     * @return
     *      List of User requester
     */
    @RequestMapping(value = "/get", method= RequestMethod.GET)
    public ResponseEntity<List<User>> getFriendRequest(Authentication authentication){

        User user=getUserFromAuthentication(authentication);

        if(user!=null && user.getFriends()!=null){
            return  new ResponseEntity<List<User>>(user.getFriendRequest(), HttpStatus.OK);
        }
        return   new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
    }


    private User getUserFromAuthentication(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = new User();
        user.setPseudo(userDetails.getUsername());
        user=userService.getUserByMailOrPseudo(user);

        return user;
    }
}
