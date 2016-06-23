package com.partinizer.data.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.partinizer.data.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * I am a data access layers to manage the User persistence.
 */
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * I am a query returning a user matching a mail.
     * @param mail The mail of the user.
     * @return A user matching the mail or null if none match.
     */
    User findByMail(String mail);

    /**
     * I am a query returning a user matching a pseudo.
     * @param pseudo The pseudo of the user.
     * @return A user matching the pseudo or null if none match.
     */
    User findByPseudo(String pseudo);

    /**
     * I am a query use to delete the friend of a user.
     * @param idUser The id of the user.
     * @param idFriend The id of the friend needed to be delete.
     */
    @Transactional
    @Modifying
    @Query(value="delete from arefriend where friend1 = ?1 and friend2=?2",nativeQuery = true)
    void deleteFriend(long idUser,long idFriend);

    /**
     * I am a query use to add a friend to a user.
     * @param idUser The id of the user.
     * @param idFriend The id of the friend needed to be add.
     */
    @Transactional
    @Modifying
    @Query(value="insert into arefriend values(?1,?2)",nativeQuery = true)
    void addFriend(long idUser,long idFriend);

    /**
     * I am a query creating a friend request.
     * @param idUser The id of the user requesting.
     * @param idFriend The id of the requested friend.
     */
    @Transactional
    @Modifying
    @Query(value="insert into askfriend values(?1,?2)",nativeQuery = true)
    void addFriendRequest(long idUser, long idFriend);

    /**
     * I am a query to discard a friend request.
     * @param idUser The id of the user requesting.
     * @param idFriend The id of the requested friend.
     */
    @Transactional
    @Modifying
    @Query(value="delete from askfriend where asker=?2 and friend=?1",nativeQuery = true)
    void deleteFriendRequest(long idUser,long idFriend);

    /**
     * I am a query to get the users whose pseudo beginning match a string.
     * @param pseudo The pseudo beginning to match.
     * @param pageable A page of the bdd.
     * @return The users whose pseudo are acceptable with the pseudo part given in parameter.
     */
    List<User> findByPseudoStartingWithOrderByPseudo(String pseudo, Pageable pageable);

    int countByPseudoStartingWith(String pseudoFilter);

    /**
     * I am a query to get the users asking a user to be friend.
     * @param idUser The id of the user receiving the invitations.
     * @return The list of the users asking to be friend with the user in parameter.
     */
    @Query(value="select a.iduser,a.firstname,a.lastname,a.pseudo,a.avatar,a.email,a.passwd,a.isactive,a.inscription from appliuser a,askfriend ask where ask.asker=?1 and ask.friend=a.iduser" ,nativeQuery = true)
    List<User> getFriendsAsking(long idUser);
    
}

