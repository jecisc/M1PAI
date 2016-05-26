package com.partinizer.front.controller;

import com.partinizer.business.exceptions.UserDoesNotExistException;
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
import java.util.List;


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

    /**
     * Update user information (lastname,firstname and avatar)
     * @param authentication
     * @param userUpdate
     * @return
     *      The user updated
     */
    @RequestMapping(value="/update", method=RequestMethod.POST)
    public ResponseEntity<User> update(Authentication authentication,@RequestBody User userUpdate){
        //TODO I'm sure this can be improve a little
        try {
            User user = getUserFromAuthentication(authentication);
            if(userUpdate!=null && userUpdate.getId()!=0 && userUpdate.getId()==user.getId()){
                try {
                    return new ResponseEntity<>(userService.updateUser(userUpdate,user),HttpStatus.OK);
                } catch (WrongInformationException e) {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (UserDoesNotExistException e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

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

    /**
     * Rest service receiving a email and a token.
     * If the token is right I will activate the account of the user matching the mail.
     * @param mail The email of the user to activate.
     * @param hash The token to verify the link.
     * @return A response containing a string with the answer.
     */
    @CrossOrigin
    @RequestMapping(value = "/validation", method = RequestMethod.GET)
    public ResponseEntity<String> validateUser(@RequestParam(value = "mail", defaultValue = "") String mail, @RequestParam(value = "cle", defaultValue = "0") String hash) {

        try {
            User user = this.userService.getUserByMail(mail);
            if (mail.hashCode() == Integer.valueOf(hash) && this.userService.validateUserSubscription(user)) {
                return new ResponseEntity<>("Votre validation a bien été effectuée.", HttpStatus.ACCEPTED);
            }
        } catch (UserDoesNotExistException e) {
            return new ResponseEntity<>("Aucun utilisateur correspondant à " + e.getMail() + " trouvé :(", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Votre lien n'est pas bon, le token est incorrect :(", HttpStatus.BAD_REQUEST);
    }

    /**
     * I am a REST service that is executed if a user forget his password.
     * @param userName The pseudo of the user.
     * @return An answer containing a JSON. The filed #message is mapped with the message to show in the front.
     */
    @CrossOrigin
    @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
    public ResponseEntity<String> forgotPassword(@RequestParam(value = "userName", defaultValue = "") String userName) {

        JSONObject jsonWriter = new JSONObject();

        try {
            User user = this.userService.getUserByPseudo(userName);
            if (this.userService.generateNewPasswordFor(user)) {
                jsonWriter.put("message", "Nouveau mot de passe envoyé.");
                return new ResponseEntity<>(jsonWriter.toJSONString(), HttpStatus.ACCEPTED);
            } else {
                jsonWriter.put("message", "Une erreur s'est produite. Veuilles réessayer.");
            }
        } catch (MessagingException e) {
            jsonWriter.put("message", "Erreur temporaire. Veuillez réessayer plus tard.");
        }catch (UserDoesNotExistException e) {
            jsonWriter.put("message", "Aucun utilisateur connu sous le nom de " + e.getPseudo() + ".");
        }

        return new ResponseEntity<>(jsonWriter.toJSONString(), HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin
    @RequestMapping(value="/get", method= RequestMethod.GET)
    public ResponseEntity<User> getUser(Authentication authentication) throws UserDoesNotExistException {

        return new ResponseEntity<>(getUserFromAuthentication(authentication), HttpStatus.OK);

    }

    @RequestMapping(value="/friends", method= RequestMethod.GET)
    public ResponseEntity<List<User>> getFriends(Authentication authentication){

        try {
            User user = getUserFromAuthentication(authentication);
            user = userService.getAllFriends(user.getId()); //TODO This is really weird. I should take a look later to understand what is going on
            return new  ResponseEntity<>(user.getFriends(),HttpStatus.OK);
        } catch (UserDoesNotExistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @RequestMapping(value="/friends/delete/{idFriend}",method=RequestMethod.DELETE)
    public ResponseEntity<User> deleteFriend(Authentication authentication,@PathVariable("idFriend") long idFriend){

        try {
            User user = getUserFromAuthentication(authentication);

            if(userService.deleteFriend(user,idFriend)){ //TODO instead of a Boolean this method should through an error.
                return new  ResponseEntity<>(user,HttpStatus.OK);
            }

            return new ResponseEntity<>(user,HttpStatus.BAD_REQUEST);
        } catch (UserDoesNotExistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    @RequestMapping(value="/search",params = {"size","page","pseudo"},method= RequestMethod.GET)
    public ResponseEntity<List<User>> searchUser(Authentication authentication,@RequestParam("size") int size,@RequestParam("page") int page,@RequestParam("pseudo") String pseudo){

        try {

            if(pseudo.isEmpty()){
                return new  ResponseEntity<>(HttpStatus.OK);
            }
            else {
                User user = getUserFromAuthentication(authentication);

                List<User> searchUsers = userService.searchUser(user, pseudo, page - 1, size);

                if (searchUsers != null) //TODO this should not be null. The previous method should raise an error if it fail.
                    return new ResponseEntity<>(searchUsers, HttpStatus.OK);

                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (UserDoesNotExistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/getNumberOfUsersFilterByPseudo",params = {"pseudoFilter"},method= RequestMethod.GET)
    public ResponseEntity<String> searchUser(Authentication authentication,@RequestParam("pseudoFilter") String pseudoFilter){

        try {
            User user = getUserFromAuthentication(authentication);
            int result=userService.getNumberOfUsersFilterByPseudo(pseudoFilter);
            JSONObject jsonWriter = new JSONObject();
            jsonWriter.put("result",result);
            return new ResponseEntity<>(jsonWriter.toJSONString(),HttpStatus.OK);

            //return new ResponseEntity<>(userService.getNumberOfUsersFilterByPseudo(pseudoFilter),HttpStatus.OK);
        }catch (UserDoesNotExistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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

    public User getUserFromAuthentication(Authentication authentication) throws UserDoesNotExistException {
        return userService.getUserByPseudo(((UserDetails) authentication.getPrincipal()).getUsername());
    }


}
