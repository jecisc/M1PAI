package com.partinizer.business.manager;

import com.partinizer.business.exceptions.ParticipantDoesNotExistException;
import com.partinizer.data.entity.Participant;
import com.partinizer.data.entity.ParticipantKeyId;
import com.partinizer.data.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vincent on 08/06/2016.
 */
@Component
public class ParticipantManager {


    private ParticipantRepository participantRepository;

    @Autowired
    public ParticipantManager(ParticipantRepository participantRepository){
        this.participantRepository=participantRepository;
    }


    public Participant create(long idEvent, long idParticipant){
        return null;
    }


    public Participant update(Participant participant) {
        participant=participantRepository.save(participant);



        return participant;
    }

    public Participant findOne(long idEvent, long idParticipant) throws ParticipantDoesNotExistException {

        ParticipantKeyId pk =createParticipantKey(idEvent,idParticipant);

        Participant participant=this.participantRepository.findOne(pk);

        if(participant==null){
            ParticipantDoesNotExistException exception = new ParticipantDoesNotExistException("No Participant object exist this key");

            throw exception;
        }

        return participant;
    }

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
