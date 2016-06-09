package com.partinizer.com.partinizer.test.manager;

import com.partinizer.PartinizerApplication;
import com.partinizer.business.exceptions.ParticipantDoesNotExistException;
import com.partinizer.business.manager.ParticipantManager;
import com.partinizer.data.entity.Participant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

/**
 * Created by vincent on 09/06/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PartinizerApplication.class)
@WebAppConfiguration
public class ParticipantManagerTest {

    @Autowired
    private ParticipantManager participantManager;

    @Test
    public void getParticipantByEventAndUser() throws ParticipantDoesNotExistException {
        Participant participant=participantManager.findOne(1,3);
        assertNotNull(participant);
    }
}
