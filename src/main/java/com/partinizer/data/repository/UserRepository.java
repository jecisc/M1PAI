package com.partinizer.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.partinizer.data.entity.User;


/**
 * Created by vincent on 10/03/16.
 */

public interface UserRepository extends JpaRepository<User,Long> {

    User findByMailOrPseudo(String mail,String pseudo);
    User findByPseudo(String pseudo);
}
