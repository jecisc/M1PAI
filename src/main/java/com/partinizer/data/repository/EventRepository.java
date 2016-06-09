package com.partinizer.data.repository;

import com.partinizer.data.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * TODO
 */
public interface EventRepository extends JpaRepository<Event, Long> {


    @Query(value="select e from Event e, Participant p, User u where u.id=p.id.idUser and p.id.idEvent=e.id and u.id=?1 and p.accepted=true")
    List<Event> getEventsByParticipantId(long idUser);

    @Query(value="select e from Event e, Participant p, User u where u.id=p.id.idUser and p.id.idEvent=e.id and u.id=?1 and p.accepted=false")
    List<Event> getEventsInvitation(long idUser);
}
