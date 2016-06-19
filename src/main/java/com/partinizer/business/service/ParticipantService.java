package com.partinizer.business.service;

import com.partinizer.business.exceptions.ParticipantDoesNotExistException;
import com.partinizer.business.manager.ParticipantManager;
import com.partinizer.data.entity.Participant;
import com.partinizer.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by vincent on 08/06/2016.
 */
@Service
public class ParticipantService {

    private ParticipantManager participantManager;

    @Autowired
    public ParticipantService(ParticipantManager participantManager){
        this.participantManager=participantManager;
    }

    /**
     * I change the participant object status to true
     * @param idEvent
     * @param idParticipant
     * @return
     * @throws ParticipantDoesNotExistException
     */
    public boolean acceptEvent(long idEvent, long idParticipant) throws ParticipantDoesNotExistException {

        Participant participant =participantManager.findOne(idParticipant,idEvent);
        participant.setAccepted(true);
        participantManager.update(participant);

        return true;
    }

    /**
     * I delete the participant
     * @param idEvent
     * @param idParticipant
     * @return
     * @throws ParticipantDoesNotExistException
     */
    public boolean denyEvent(long idEvent, long idParticipant) throws ParticipantDoesNotExistException {

        Participant participant =participantManager.findOne(idParticipant,idEvent);

        participantManager.delete(participant);

        return true;
    }

    public void deleteParticipate(User user, long idEvent) throws ParticipantDoesNotExistException {

        this.participantManager.findOne(user.getId(),idEvent);
        this.participantManager.delete(this.participantManager.findOne(user.getId(),idEvent));
    }

}
