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


    private UserRepository userRepository;

    @Autowired
    public UserManager(UserRepository userRepository){
        this.userRepository=userRepository;
    }


    public User createUser(User user){
        user=userRepository.save(user);

        return user;
    }

}
