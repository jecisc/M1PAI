package com.partinizer.data.repository;

import com.partinizer.data.entity.Participant;
import com.partinizer.data.entity.ParticipantKeyId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vincent on 08/06/2016.
 */
public interface ParticipantRepository extends JpaRepository<Participant,ParticipantKeyId> {


}
