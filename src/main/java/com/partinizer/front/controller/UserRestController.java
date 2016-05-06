package com.partinizer.front.controller;

import com.partinizer.business.exceptions.WrongInformationException;
import com.partinizer.business.service.UserService;
import com.partinizer.data.entity.User;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.constraints.Null;


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
    public ResponseEntity<String> createUser(@RequestBody User user) {

        JSONObject jsonWriter = new JSONObject();

        try {
            userService.createUser(user);
            jsonWriter.put("message", "Utilisateur créé.");
            return new ResponseEntity<>(jsonWriter.toJSONString(), HttpStatus.CREATED);
        } catch (WrongInformationException e) {
            jsonWriter.put("message", e.getFrontMessage());
        } catch (NullPointerException e) {
            jsonWriter.put("message", "Informations incomplétes.");
        }

        return new ResponseEntity<>(jsonWriter.toJSONString(), HttpStatus.BAD_REQUEST);

    }

    @CrossOrigin
    @RequestMapping(value = "/validation", method = RequestMethod.GET)
    public ResponseEntity<String> validateUser(@RequestParam(value = "mail", defaultValue = "") String mail, @RequestParam(value = "cle", defaultValue = "0") String hash) {

        User user = this.userService.getUserByMail(mail);

        if (!(user == null) && (mail.hashCode() == Integer.valueOf(hash)) && this.userService.validateUserSubscription(user)) {
            return new ResponseEntity<>("Votre validation a bien été effectuée.", HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>("Votre lien n'est pas bon :(", HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin
    @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
    public ResponseEntity<String> forgotPassword(@RequestParam(value = "userName", defaultValue = "") String userName) {

        User user = this.userService.getUserByPseudo(userName);
        JSONObject jsonWriter = new JSONObject();

        try {
            if (!(user == null) && this.userService.generateNewPasswordFor(user)) {
                jsonWriter.put("message", "Nouveau mot de passe envoyé.");
                return new ResponseEntity<>(jsonWriter.toJSONString(), HttpStatus.ACCEPTED);
            } else {
                jsonWriter.put("message", "Utilisateur inconnu. Veuillez donner un pseudo correct.");
            }
        } catch (MessagingException e) {
            jsonWriter.put("message", "Erreur temporaire. Veuillez reéssayer plus tard.");
        }

        return new ResponseEntity<>(jsonWriter.toJSONString(), HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin
    @RequestMapping(value="/get", method= RequestMethod.GET)
    public ResponseEntity<User> getUser(Authentication authentication){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return new ResponseEntity<>(userService.getUserByPseudo(userDetails.getUsername()), HttpStatus.OK);

    }

    @RequestMapping(value="/friends", method= RequestMethod.GET)
    public ResponseEntity<User> getFriends(Authentication authentication){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = new User();
        user.setPseudo(userDetails.getUsername());
        user=userService.getUserByMailOrPseudo(user);

        user=userService.getAllFriends(user.getId());

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
    @ResponseStatus(value=HttpStatus.EXPECTATION_FAILED)
    public String handleException(){

        return "Error";

    }
}
