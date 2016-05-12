package com.partinizer.data.repository;

import org.springframework.data.domain.Page;
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

    //TODO see same method comment on UserService
    User findByMailOrPseudo(String mail,String pseudo);
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
    @Query(value="delete from askfriend where asker=?2 and friend=?1",nativeQuery = true)
    void deleteFriendRequest(long idUser,long idFriend);


    @EntityGraph(value="User.friends", type= EntityGraph.EntityGraphType.LOAD)
    User getFriendsById(long id);

    /*@Query(value="select iduser,firstname,lastname from appliuser where firstname like ?1 limit 10 offset ?2",nativeQuery = true)
    List<User> searchUser(String name,int interval);*/

    /*@Query(value="select u.id, u.name, u.firstname from User u where u.firstname LIKE :name% limit 10 offset :interval " )
    List<User> searchUser(String name,int interval);*/

    List<User> findByNameStartingWithOrderByName(String lastname, Pageable pageable);

}

