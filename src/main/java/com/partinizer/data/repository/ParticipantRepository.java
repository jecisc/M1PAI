package com.partinizer.data.repository;

import com.partinizer.data.entity.Participant;
import com.partinizer.data.entity.ParticipantKeyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vincent on 08/06/2016.
 */
public interface ParticipantRepository extends JpaRepository<Participant,ParticipantKeyId> {

    /*@Transactional
    @Modifying
    @Query(value="delete from participate where appliuser=?1 and event=?2",nativeQuery = true)
    Participant deleteParticipate(long idUser,long idEvent);*/
}
