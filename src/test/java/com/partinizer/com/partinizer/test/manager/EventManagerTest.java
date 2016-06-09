package com.partinizer.com.partinizer.test.manager;

/**
 * Created by vincent on 07/06/2016.
 */

import com.partinizer.PartinizerApplication;
import com.partinizer.business.exceptions.EventDoesNotExistException;
import com.partinizer.business.manager.EventManager;
import com.partinizer.data.entity.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PartinizerApplication.class)
@WebAppConfiguration
public class EventManagerTest {

    @Autowired
    private EventManager eventManager;

    @Test
    public void getEventsByParticipantId() throws EventDoesNotExistException {

        /*List<Event> events=eventManager.getEventsByParticipantId(3);
        assertNotNull(events);*/
    }
}
