package com.ameda.kisevu.works.services;
/*
*
@author ameda
@project java-security
*
*/

import com.ameda.kisevu.works.entities.User;
import com.ameda.kisevu.works.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

}
