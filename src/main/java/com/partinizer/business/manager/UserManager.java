package com.partinizer.business.manager;

import com.partinizer.data.entity.User;
import com.partinizer.data.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by vincent on 10/03/16.
 */
@Component
@Transactional
public class UserManager {

    private String regexPassword="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,40}$";
    private String regexMail="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private UserRepository userRepository;

    @Autowired
    public UserManager(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    //TODO limiter le nombre de caractère  password, confirmation password

    /**
     * Create a User entity if all require fields are fill
     * @param user
     * @return
     *      The User created or null if not
     */
    public User createUser(User user){

        if(checkPseudo(user.getPseudo()) && checkPassword(user.getPassword())
                && checkMail(user.getMail()) && checkName(user.getName()) &&
                checkFirstName(user.getFirstName()) && checkActive(user.isActive()))
        {
            /* Check if mail or pseudo already exist */
            User user_exist=getUserByMailOrPseudo(user);

            if(user_exist==null) {
                user.setRegistDate(new Date(System.currentTimeMillis()));
                user = userRepository.save(user);

                return user;
            }
        }

        return null;
    }

    /**
     * Get a User entity by his id
     * @param user
     * @return
     *      The User obtained or null if not
     */
    public User getUser(User user){
        if(validUser(user)){
            user=userRepository.findOne(user.getId());

            return user;
        }

        return null;
    }

    /**
     * Get a User entity by his mail or his pseudo
     * @param user
     * @return
     *      The user obtained or null if not
     */
    public User getUserByMailOrPseudo(User user){

        if(user!=null && (user.getMail()!=null && !user.getMail().equals(""))||
                (user.getPseudo()!=null && !user.getPseudo().equals("")) ) {
            user = userRepository.findByMailOrPseudo(user.getMail(),user.getPseudo());

            return user;
        }

        return null;
    }

    public User getAllFriends(long id){

        User user= userRepository.getFriendsById(id);


        return user;


    }

    /**
     * Set the active status to true
     * @param user
     */
    public void setActiveStatusTrue(User user){

    }

    public List<User> findByPseudo(String pseudo){

        List<User> userList=userRepository.findByPseudoStartingWith(pseudo);

        return userList;
    }





    /**
     * Delete a User by his Id
     * @param user
     * @return
     *      The User deleted or null if not
     */
    public User deleteUser(User user){

        if(validUser(user)&& userExist(user)){
            userRepository.delete(user);

            return user;
        }

        return null;
    }


    private boolean validUser(User user){

        return user!=null && user.getId()!=0;
    }


    private boolean userExist(User user){

        user = getUser(user);

        if(user!=null)
            return true;

        return false;
    }

    private boolean checkPseudo(String pseudo){

        return pseudo!=null && !pseudo.equals("") && pseudo.length()>=4 && pseudo.length()<=20;
    }

    private boolean checkPassword(String password){

        return password!=null && !password.equals("") && password.matches(regexPassword);
    }

    private boolean checkMail(String mail){

        return mail!=null && !mail.equals("") && mail.matches(regexMail);
    }

    private boolean checkName(String name){
        return name!=null && !name.equals("") && name.length()>=3 && name.length()<=40;
    }

    private boolean checkFirstName(String firstName){
        return firstName!=null && !firstName.equals("") && firstName.length()>=3 && firstName.length()<=40;
    }

    private boolean checkActive(Boolean active){
        return !active;
    }


}
