package com.partinizer.business.manager;

import com.partinizer.business.exceptions.ParticipantDoesNotExistException;
import com.partinizer.data.entity.Participant;
import com.partinizer.data.entity.ParticipantKeyId;
import com.partinizer.data.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * I am a manager which should contains the behavior of the Participants of the project.
 */
@Component
public class ParticipantManager {


    protected ParticipantRepository participantRepository;

    @Autowired
    public ParticipantManager(ParticipantRepository participantRepository){
        this.participantRepository=participantRepository;
    }

    public Participant update(Participant participant) {
        participant=participantRepository.save(participant);

        return participant;
    }

    public Participant findOne(long idParticipant,long idEvent) throws ParticipantDoesNotExistException {

        Participant participant = participantRepository.findOne(createParticipantKey(idEvent,idParticipant));

        if(participant==null){
            throw new ParticipantDoesNotExistException("No Participant object exist this key");
        }

        return participant;
    }

    //TODO WTF?
    public boolean delete(Participant participant) {
        this.participantRepository.delete(participant);
        return true;
    }

    private ParticipantKeyId createParticipantKey(long idEvent, long idParticipant){
        ParticipantKeyId pk= new ParticipantKeyId();
        pk.setIdEvent(idEvent);
        pk.setIdUser(idParticipant);
        return pk;
    }

}
