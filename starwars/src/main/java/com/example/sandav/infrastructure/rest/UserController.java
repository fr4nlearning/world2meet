package com.example.sandav.infrastructure.rest;

import com.example.sandav.application.services.UserService;
import com.example.sandav.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController implements IUserController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ResponseEntity<User> save(User user) {
        /*user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userService.save(user));*/
        String encode= bCryptPasswordEncoder.encode(user.password());
        return ResponseEntity.ok(userService.save(
                new User(user.id(), user.email(), encode, user.rol())
        ));
    }
}
