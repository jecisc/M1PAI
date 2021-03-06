package com.partinizer.front.controller;

import com.partinizer.business.exceptions.UserDoesNotExistException;
import com.partinizer.business.service.UserService;
import com.partinizer.data.entity.User;
import org.json.simple.JSONObject;
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

    @RequestMapping(value = "/ask/{idFriend}", method= RequestMethod.GET)
    public ResponseEntity<String> ask(Authentication authentication,@PathVariable("idFriend") long idFriend){

        try {
            User user = getUserFromAuthentication(authentication);
            if(userService.addFriendRequest(user,idFriend)){ //TODO remove the if and return an error at a lower level if there is a problem
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (UserDoesNotExistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/accept/{idFriend}", method= RequestMethod.GET)
    public ResponseEntity<String> accept(Authentication authentication,@PathVariable("idFriend") long idFriend){

        try {
            User user = getUserFromAuthentication(authentication);
            if(userService.addFriend(user,idFriend)){ //TODO remove the if and return an error at a lower level if there is a problem
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (UserDoesNotExistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/deny/{idFriend}", method= RequestMethod.GET)
    public ResponseEntity<String> deny(Authentication authentication,@PathVariable("idFriend") long idFriend){

        try {
            User user = getUserFromAuthentication(authentication);
            if(userService.denyFriendRequest(user,idFriend)){ //TODO remove the if and return an error at a lower level if there is a problem
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (UserDoesNotExistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Get friend requests
     * @param authentication
     * @return
     *      List of User requester
     */
    @RequestMapping(value = "/get", method= RequestMethod.GET)
    public ResponseEntity<List<User>> getFriendRequest(Authentication authentication){

        try {
            User user = getUserFromAuthentication(authentication);
            if(user.getFriends()!=null){ //TODO getFriends should not be able to return null but raise an error if there is a problem
                return new ResponseEntity<>(user.getFriendRequests(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (UserDoesNotExistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    // TODO this is bad. Instead I should directly use a request that will do a `count(*)` but for now, no time.
    @RequestMapping(value = "/numberOfInvitations", method= RequestMethod.GET)
    public ResponseEntity<String> getNumberOfFriendRequest(Authentication authentication){

        JSONObject jsonWriter = new JSONObject();

        try {
            // TODO this is bad. Instead I should directly use a request that will do a `count(*)` but for now, no time.
            User user = getUserFromAuthentication(authentication);
            if(user.getFriends()!=null){ //TODO getFriends should not be able to return null but raise an error if there is a problem
                jsonWriter.put("number", String.valueOf(user.getFriendRequests().size()));
                return new ResponseEntity<>(jsonWriter.toJSONString(), HttpStatus.OK);
            }
            jsonWriter.put("number", null);
        } catch (UserDoesNotExistException e) {
            jsonWriter.put("number", null);
        }

        return new ResponseEntity<>(jsonWriter.toJSONString(), HttpStatus.BAD_REQUEST);

    }

    public User getUserFromAuthentication(Authentication authentication) throws UserDoesNotExistException {
        return userService.getUserByPseudo(((UserDetails) authentication.getPrincipal()).getUsername());
    }

    @RequestMapping(value = "/getFriendAsking", method= RequestMethod.GET)
    public ResponseEntity<List<User>> getFriendsAsking(Authentication authentication){

        try {
            User user = getUserFromAuthentication(authentication);

            return new ResponseEntity<>(userService.getFriendsAsking(user), HttpStatus.OK);


        } catch (UserDoesNotExistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
