package com.ameda.works.chat_oscaris.api;


import com.ameda.works.chat_oscaris.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    public ResponseEntity<?> getUsers( Authentication currentUser ){
        return new ResponseEntity<>(userService.getAllUsersExceptMe(currentUser), HttpStatus.OK);
    }
}
