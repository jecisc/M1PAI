package com.partinizer.front.controller;

import com.partinizer.business.service.UserService;
import com.partinizer.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

        if(user.getPassword().equals(user_.getPassword()))
           return new ResponseEntity<String>(HttpStatus.ACCEPTED);

        //Mot de passe non correcte
        return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * Update user information (lastname,firstname and avatar)
     * @param authentication
     * @param userUpdate
     * @return
     *      The user updated
     */
    @RequestMapping(value="/update", method=RequestMethod.POST)
    public ResponseEntity<User> update(Authentication authentication,@RequestBody User userUpdate){

        User user=getUserFromAuthentication(authentication);

        if(userUpdate!=null && userUpdate.getId()!=0 && userUpdate.getId()==user.getId()){
            userUpdate=userService.updateUser(userUpdate,user);
            if(userUpdate!=null){
                return new ResponseEntity<User>(userUpdate,HttpStatus.OK);
            }
                return new ResponseEntity<User>(userUpdate,HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<User>(userUpdate,HttpStatus.BAD_REQUEST);
    }




    /**Méthode de création d'un utilisateur basé sur une requête HTTP POST**/
    @CrossOrigin
    @RequestMapping(value = "/create", method=RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user){

        if(user==null){
            return new ResponseEntity<>(user,HttpStatus.BAD_REQUEST);
        }

        user= userService.createUser(user);

        if(user==null){
            return new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST);
        }

            return new ResponseEntity<>(user,HttpStatus.CREATED);

    }

    @CrossOrigin
    @RequestMapping(value = "/validation", method = RequestMethod.GET)
    public ResponseEntity<Boolean> validateUser(@RequestParam(value = "mail", defaultValue = "") String mail, @RequestParam(value = "cle", defaultValue = "0") String hash) {

        User user = this.userService.getUserByMail(mail);

        if (!(user == null) && (mail.hashCode() == Integer.valueOf(hash)) && this.userService.validateUserSubscription(user)) {
            return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin
    @RequestMapping(value="/get", method= RequestMethod.GET)
    public ResponseEntity<User> getUser(Authentication authentication){

        User user=getUserFromAuthentication(authentication);

        return new ResponseEntity<User>(user
                , HttpStatus.OK);

    }

    @RequestMapping(value="/friends", method= RequestMethod.GET)
    public ResponseEntity<User> getFriends(Authentication authentication){

        User user=getUserFromAuthentication(authentication);

        user=userService.getAllFriends(user.getId());

        if(user!=null)
            return new  ResponseEntity<User>(user,HttpStatus.OK);

        return new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value="/friends/delete/{idFriend}",method=RequestMethod.DELETE)
    public ResponseEntity<User> deleteFriend(Authentication authentication,@PathVariable("idFriend") long idFriend){

        User user=getUserFromAuthentication(authentication);

        if(userService.deleteFriend(user,idFriend))
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
    @ResponseStatus(value=HttpStatus.EXPECTATION_FAILED)
    public String handleException(){

        return "Error";

    }

    private User getUserFromAuthentication(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = new User();
        user.setPseudo(userDetails.getUsername());
        user=userService.getUserByMailOrPseudo(user);

        return user;
    }
}
