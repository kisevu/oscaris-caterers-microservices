package com.ameda.works.chat_oscaris.repositories;


import com.ameda.works.chat_oscaris.entities.common.constants.UserConstants;
import com.ameda.works.chat_oscaris.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    @Query(name = UserConstants.FIND_USER_BY_EMAIL)
    Optional<User> findByEmail(@Param("email") String email);
}
