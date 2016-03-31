package com.partinizer.com.partinizer.test.manager;

import com.partinizer.PartinizerApplication;
import com.partinizer.business.manager.UserManager;
import com.partinizer.data.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by vincent on 27/03/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PartinizerApplication.class)
@WebAppConfiguration
public class UserManagerTest {

    @Autowired
    private UserManager userManager;

    @Test
    public void getUserByIdTest(){

        User user = new User();
        user.setId(11);
        user=userManager.getUser(user);
        assertNotNull(user);
    }

    @Test
    public void getUserByMail(){
        User user = new User();
        user.setMail("jalzuritro@gmail.com");
        user=userManager.getUserByMail(user);
        assertNotNull(user);
    }
}
