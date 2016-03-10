package com.partinizer.business.manager;

import com.partinizer.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by vincent on 10/03/16.
 */
@Component
public class UserManager {

    @Autowired
    private UserRepository userRepository;

    public UserManager(UserRepository userRepository){
        this.userRepository=userRepository;
    }


    }
}
