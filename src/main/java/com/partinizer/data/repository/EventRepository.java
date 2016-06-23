package com.partinizer.data.repository;

import com.partinizer.data.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * I am a data access layers to manage the Event persistence.
 */
public interface EventRepository extends JpaRepository<Event, Long> {


    /**
     * I am a query that will request all the events accepted by a participant.
     * @param idUser The id of a participant.
     * @return A list of events accepted by the participant.
     */
    @Query(value = "select e from Event e, Participant p, User u where u.id=p.id.idUser and p.id.idEvent=e.id and u.id=?1 and p.accepted=true")
    List<Event> getEventsByParticipantId(long idUser);

    /**
     * I am a query that will request the events invitations of a participant.
     * @param idUser The id of a participant.
     * @return A list of events the participant is invited to.
     */
    @Query(value = "select e from Event e, Participant p, User u where u.id=p.id.idUser and p.id.idEvent=e.id and u.id=?1 and p.accepted=false")
    List<Event> getEventsInvitationsOf(long idUser);

    /**
     * I am a query that will request all the events created by a user.
     * @param idUser The id of the User.
     * @return A list of event created by the User.
     */
    @Query(value = "select distinct e from Event e where  e.creator.id=?1")
    List<Event> getEventsCreatedBy(long idUser);


}
