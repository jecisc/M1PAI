package com.partinizer.com.partinizer.test.manager;

import com.partinizer.PartinizerApplication;
import com.partinizer.business.exceptions.WrongInformationException;
import com.partinizer.business.manager.UserManager;
import com.partinizer.data.entity.User;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import java.util.Date;
import java.util.List;


/**
 * Created by vincent on 27/03/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PartinizerApplication.class)
@WebAppConfiguration
public class UserManagerTest {

    @Autowired
  private UserManager userManager;

    private User user;

    @Before
    public void init(){
        user = new User();
        user.setPseudo(RandomStringUtils.randomAlphabetic(20));
        user.setMail( RandomStringUtils.randomAlphabetic(20)+"@mail.com");
        user.setName( RandomStringUtils.randomAlphabetic(20));
        user.setFirstName( RandomStringUtils.randomAlphabetic(20));
        user.setPassword(RandomStringUtils.randomAlphabetic(20));
        user.setRegistDate(new Date(System.currentTimeMillis()));
        user.setAvatar("");
        user.setActive(false);
    }


    @Test
    public void getUserById(){

        user.setId(11);
        user=userManager.getUser(user);
        assertNotNull(user);
    }

    @Test
    public void getUserByMail(){
        user.setMail("superman@gmail.com");
        user=userManager.getUserByMailOrPseudo(user);
        assertNotNull(user);
    }

    @Test
    public void getUserByBadMail(){
        user.setMail("badmail@mail.com");
        user=userManager.getUserByMailOrPseudo(user);
        assertNull(user);
    }

    @Test
    public void getUserByPseudo(){
        user.setPseudo("superman");
        user=userManager.getUserByMailOrPseudo(user);
        assertEquals("superman@gmail.com",user.getMail());
    }

    @Test
    public void getUserByBadPseudo(){
        user.setPseudo("badpseudo");
        user=userManager.getUserByMailOrPseudo(user);
        assertNull(user);
    }


    @Test
    public void createUser(){

        user.setPassword("fkjd5sfldskmM");

        try {
            userManager.createUser(user);
            assert true;
        } catch (WrongInformationException e) {
            assert false;
        }
    }


    @Test
    public void createUserWithBadPassword(){

        user.setPassword("fkjd5sfldskm");
        user.setMail("mailBidon@mail.com");
        try {
            userManager.createUser(user);
            assert false;
        } catch (WrongInformationException e) {
            assert true;
        }

    }

    @Test
    public void createUserWithBadMail(){
        user.setMail("mailbidonghfsdhkjfg");
        user.setPassword("fkjd5sfldskmM");
        try {
            userManager.createUser(user);
            assert false;
        } catch (WrongInformationException e) {
            assert true;
        }
    }

    @Test
    public void createUserWithMailExist(){

        user.setPseudo("pseudoTest_1");
        user.setMail("mailTest_1@gmail.com");
        user.setPassword("fkjd5sfldskmM");
        try {
            userManager.createUser(user);
            assert false;
        } catch (WrongInformationException e) {
            assert true;
        }
    }

    @Test
    public void createUserWithActiveTrue(){
        user.setActive(true);
        user.setPassword("fkjd5sfldskmM");
        try {
            userManager.createUser(user);
            assert false;
        } catch (WrongInformationException e) {
            assert true;
        }
    }

    @Test
    public void findUserByPseudo(){
        user.setPseudo("teo");
        List<User> userList=userManager.findByPseudo("teo");
        assertNotNull(userList);

    }
}
