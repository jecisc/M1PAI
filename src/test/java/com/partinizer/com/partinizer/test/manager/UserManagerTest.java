package com.partinizer.com.partinizer.test.manager;

import com.partinizer.PartinizerApplication;
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
    public void createUser(){

        user.setPassword("Password59");

        user=userManager.createUser(user);
        assertNotNull(user);
    }

    @Test
    public void getUserById(){

        user.setId(1);
        user=userManager.getUser(user);
        assertNotNull(user);
    }

    @Test
    public void getUserByMail(){
        user.setMail("robert.deniro@mail.com");
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
        user.setPseudo("cbronson");
        user=userManager.getUserByMailOrPseudo(user);
        assertEquals("charles.bronson@mail.com",user.getMail());
    }

    @Test
    public void getUserByBadPseudo(){
        user.setPseudo("badpseudo");
        user=userManager.getUserByMailOrPseudo(user);
        assertNull(user);
    }

   @Test
    public void createUserWithBadPassword(){

        user.setPassword("fkjd5sfldskm");
        user.setMail("mailBidon@mail.com");
        user=userManager.createUser(user);
        assertNull(user);
    }

    @Test
    public void createUserWithBadMail(){
        user.setMail("mailbidonghfsdhkjfg");
        user.setPassword("fkjd5sfldskmM");
        user=userManager.createUser(user);
        assertNull(user);
    }

    @Test
    public void createUserWithMailExist(){

        user.setPseudo("pseudoTest_1");
        user.setMail("charles.bronson@mail.com");
        user.setPassword("fkjd5sfldskmM");
        user=userManager.createUser(user);
        assertNull(user);
    }

    @Test
    public void createUserWithActiveTrue(){
        user.setActive(true);
        user.setPassword("fkjd5sfldskmM");
        user=userManager.createUser(user);
        assertNull(user);
    }

    @Test
    public void findUserByPseudo(){
        user.setPseudo("bpit");
        List<User> userList=userManager.findByPseudo("bpit");
        assertNotNull(userList);

    }

   /* @Test
    public void deleteFriendById(){
        user.setPseudo("vmargerin");
        user=userManager.getUserByMailOrPseudo(user);
        userManager.deleteFriend(user,5);
    }*/

    @Test
    public void getFriendsById(){
        user=userManager.getAllFriends(1);
        assertEquals(3,user.getFriends().size());

    }

  /*  @Test
    public void updateUser(){
        user.setId(1);
        user=userManager.getUser(user);
        user.setAvatar("test");
        user=userManager.updateUser(user);

        assertEquals("test",user.getAvatar());
    }*/
}
