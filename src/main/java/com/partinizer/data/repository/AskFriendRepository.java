package com.partinizer.data.repository;

import com.partinizer.data.entity.AskFriend;
import com.partinizer.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vincent on 25/04/2016.
 */
public interface AskFriendRepository extends JpaRepository<AskFriend,Long> {
}
