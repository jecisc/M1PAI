package com.partinizer.data.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import com.partinizer.data.entity.User;

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

    @EntityGraph(value="User.detail", type= EntityGraph.EntityGraphType.LOAD)
    User getFriendsById(long id);
}

