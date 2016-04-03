package com.partinizer.business.manager;

import com.partinizer.data.entity.User;
import com.partinizer.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vincent on 10/03/16.
 */
@Component
public class UserManager {

    private String regexPassword="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\\\S+$).{8,40}";
    private UserRepository userRepository;

    @Autowired
    public UserManager(UserRepository userRepository){
        this.userRepository=userRepository;
    }


    public User createUser(User user){

        if(checkPseudo(user.getPseudo()) && checkPassword(user.getPassword())){
            return userRepository.save(user);
        }

        return null;
    }

    public User getUser(User user){
        if(validUser(user)){
            return userRepository.findOne(user.getId());
        }

        return null;
    }


    public User getUserByMail(User user){

        if(user!=null && user.getMail()!=null && !user.getMail().equals("")) {
            return userRepository.findByMail(user.getMail());
        }

        return null;
    }

    public User getUserByPseudo(User user){

        if(user!=null && user.getPseudo()!=null && !user.getPseudo().equals("")) {
            return userRepository.findByPseudo(user.getPseudo());
        }

        return null;
    }

    public User deleteUser(User user){

        if(validUser(user)&& userExist(user)){
            userRepository.delete(user);

            return user;
        }

        return null;
    }

    /**
     *
     * Méthode qui contrôle si l'objet User est valide
     * @param user
     */
    private boolean validUser(User user){

        return user!=null && user.getId()!=0;
    }


    private boolean userExist(User user){

        return getUser(user) != null;
    }

    private boolean checkPseudo(String pseudo){

        return pseudo!=null && pseudo.length()>4 && pseudo.length()<20;
    }

    private boolean checkPassword(String password){

        return password!=null && !password.equals("") && password.matches(regexPassword);
    }



}
