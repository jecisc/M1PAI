package com.partinizer.com.partinizer.test.manager;

import com.partinizer.PartinizerApplication;
import com.partinizer.data.entity.AskFriend;
import com.partinizer.data.repository.AskFriendRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by vincent on 25/04/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PartinizerApplication.class)
@WebAppConfiguration
public class AskFriendManagerTest {

    @Autowired
    AskFriendRepository repository;

    @Test
    public void test(){

        List<AskFriend> af=repository.findAll();
        System.out.println("test");
    }
}
