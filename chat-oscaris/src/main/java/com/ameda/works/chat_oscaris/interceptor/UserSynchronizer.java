package com.ameda.works.chat_oscaris.interceptor;

import com.ameda.works.chat_oscaris.entities.user.User;
import com.ameda.works.chat_oscaris.interceptor.mapper.UserMapper;
import com.ameda.works.chat_oscaris.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserSynchronizer {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void synchronizeWithIdp(Jwt token) {
        log.info("Synchronizing user with idp>>");
        getUserEmail(token).ifPresent( usrEmail -> {
            log.info("Synchronizing user with email{}",usrEmail);
            Optional<User> optUser=  userRepository.findByEmail(usrEmail);
            User user = userMapper.fromTokenAttributes(token.getClaims());
            optUser.ifPresent(value->user.setId(optUser.get().getId()));
            userRepository.save(user);
        });
    }

    private Optional<String> getUserEmail (Jwt token ){
        Map<String, Object> attributes = token.getClaims();
        if( attributes.containsKey("email")){
            return Optional.of(attributes.get("email").toString());
        }
        return Optional.empty();
    }
}
