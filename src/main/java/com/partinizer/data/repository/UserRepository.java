package com.partinizer.data.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import com.partinizer.data.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by vincent on 10/03/16.
 */

public interface UserRepository extends JpaRepository<User,Long> {

    User findByMail(String mail);
    User findByPseudo(String pseudo);
    List<User> findByPseudoStartingWith(String pseudo);

    @Transactional
    @Modifying
    @Query(value="delete from arefriend where friend1 = ?1 and friend2=?2",nativeQuery = true)
    void deleteFriend(long idUser,long idFriend);

    @Transactional
    @Modifying
    @Query(value="insert into arefriend values(?1,?2)",nativeQuery = true)
    void addFriend(long idUser,long idFriend);


    @Transactional
    @Modifying
    @Query(value="insert into askfriend values(?1,?2)",nativeQuery = true)
    void addFriendRequest(long idUser, long idFriend);


    @Transactional
    @Modifying
    @Query(value="delete from askfriend where asker=?2 and friend=?1",nativeQuery = true)
    void deleteFriendRequest(long idUser,long idFriend);


    @EntityGraph(value="User.friends", type= EntityGraph.EntityGraphType.LOAD)
    User getFriendsById(long id);

    @EntityGraph(value="User.friendRequest", type= EntityGraph.EntityGraphType.LOAD)
    User getFriendRequestById(long id);

    /*@Query(value="select iduser,firstname,lastname from appliuser where firstname like ?1 limit 10 offset ?2",nativeQuery = true)
    List<User> searchUser(String name,int interval);*/

    /*@Query(value="select u.id, u.name, u.firstname from User u where u.firstname LIKE :name% limit 10 offset :interval " )
    List<User> searchUser(String name,int interval);*/

    List<User> findByPseudoStartingWithOrderByPseudo(String pseudo, Pageable pageable);

    /*@Query(value="select count(pseudo) from appliuser where pseudo like '%?1'",nativeQuery = true)
    int getNumberOfUsersFilterByPseudo(String pseudoFilter);*/

    int countByPseudoStartingWith(String pseudoFilter);

    @Query(value="select a.iduser,a.firstname,a.lastname,a.pseudo,a.avatar,a.email,a.passwd,a.isactive,a.inscription from appliuser a,askfriend ask where ask.asker=?1 and ask.friend=a.iduser" ,nativeQuery = true)
    List<User> getFriendsAsking(long idUser);
}

