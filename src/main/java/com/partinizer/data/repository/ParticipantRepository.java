package com.partinizer.data.repository;

import com.partinizer.data.entity.Participant;
import com.partinizer.data.entity.ParticipantKeyId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * I am a data access layers to manage the Participant persistence.
 */
public interface ParticipantRepository extends JpaRepository<Participant, ParticipantKeyId> {

}