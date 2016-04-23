package com.partinizer.front.controller;

import com.partinizer.business.service.UserService;
import com.partinizer.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


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


    @CrossOrigin
    @RequestMapping(value="/{pseudo}", method= RequestMethod.GET)
    public ResponseEntity<User> userTest(Principal principal,
                                         @PathVariable("pseudo") String pseudo){
        User user = new User();
        user.setPseudo(pseudo);
        user=userService.getUserByMailOrPseudo(user);

        return new ResponseEntity<User>(user
                , HttpStatus.OK);

    }

    /*@RequestMapping(value = "", method=RequestMethod.GET)
    public Principal login(Principal user){

        return user;
    }*/
    @CrossOrigin
    @RequestMapping(value = "/login", method=RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody User user){
        if(user==null && user.getMail()!=null && user.getPassword()!=null){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

        User user_=userService.getUserByMailOrPseudo(user);

        //On vérifie que la requête à renvoyé quelque chose à partir du mail
        if(user_==null){
            return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
        }

        if(user.getPassword().equals(user_.getPassword()) && user.isActive())
           return new ResponseEntity<String>(HttpStatus.ACCEPTED);

        //Mot de passe non correcte
        return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
    }




    /**Méthode de création d'un utilisateur basé sur une requête HTTP POST**/
    @CrossOrigin
    //@PreAuthorize("true")
    @RequestMapping(value = "/create", method=RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user){

        if(user==null){
            return new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST);
        }

        user= userService.createUser(user);

        if(user==null){
            return new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST);
        }

            return new ResponseEntity<User>(user,HttpStatus.CREATED);

    }

    @RequestMapping(value="/friends/{idUser}", method= RequestMethod.GET)
    public ResponseEntity<User> userTest(
            @PathVariable("idUser") long idUser){

            User user=userService.getAllFriends(idUser);

            if(user!=null)
                return new  ResponseEntity<User>(user,HttpStatus.OK);

            return new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST);
    }



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
