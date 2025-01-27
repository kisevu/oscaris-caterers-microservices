package com.ameda.works.chat_oscaris.services;


import com.ameda.works.chat_oscaris.interceptor.mapper.UserMapper;
import com.ameda.works.chat_oscaris.repositories.UserRepository;
import com.ameda.works.chat_oscaris.responses.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public List<UserResponse> getAllUsersExceptMe(Authentication currentUser ){
        return userRepository.findAllUsersExceptSelf(currentUser.getName())
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }


}
