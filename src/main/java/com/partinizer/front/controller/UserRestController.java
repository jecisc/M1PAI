package com.partinizer.front.controller;

import com.partinizer.business.service.UserService;
import com.partinizer.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



/**
 * Created by vincent on 10/03/16.
 */
@RestController
@RequestMapping("/user")
public class UserRestController {


    private UserService userService;

    @Autowired
    public UserRestController(UserService userService){
        this.userService=userService;
    }

    /**Méthode de création d'un utilisateur basé sur une requête HTTP POST**/
    @CrossOrigin
    @RequestMapping(value = "/create", method=RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user){

        if(user==null){
            return new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST);
        }

        user= userService.createUser(user);


            return new ResponseEntity<User>(user,HttpStatus.CREATED);

    }

    /*@RequestMapping(value="/{user}", method= RequestMethod.GET)
    public ResponseEntity<User> userTest(
            @PathVariable("user") String user_){



       User user= new User();
        user.setName("teo");
        user.setMail("teo.brisse@gmail.com");
        user.setPassword("password");
        user.setRegistDate(new Date(System.currentTimeMillis()));
        user.setActive(true);
        user.setPseudo("teobrisse");
        user.setAvatar("");





        return new ResponseEntity<User>(
                , HttpStatus.OK);

    }*/

    /**
     * Méthode qui gère les exceptions qui peuvent arriver dans les différentes couches
     * @return
     *      Un message d'erreur ainsi qu'un code statut HTTP 400
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    public String handleException(){

        return "Error";

    }
}
