package com.partinizer.data.repository;

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

    User findByMailOrPseudo(String mail,String pseudo);
    User findByMail(String mail);
    /* @Query(value="Select u from User u where u.pseudo=:pseudo or u.mail=:mail ")
     User findByMailOrPseudo(@Param("mail")String mail,@Param("pseudo")String pseudo);*/
    // User findByPseudo(String pseudo);
    List<User> findByPseudoStartingWith(String pseudo);

    @Transactional
    @Modifying
    @Query(value="delete from arefriend where friend1 = ?1 and friend2=?2",nativeQuery = true)
    void deleteFriend(long idUser,long idFriend);

    @EntityGraph(value="User.detail", type= EntityGraph.EntityGraphType.LOAD)
    User getFriendsById(long id);
}

