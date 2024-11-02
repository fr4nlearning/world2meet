package com.example.sandav.application.services;

import com.example.sandav.domain.model.User;
import com.example.sandav.domain.port.IUserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService {
    private final IUserRepository iUserRepository;

    public User save(User user){
        return this.iUserRepository.save(user);
    }

    public User findByEmail(String email){
        return this.iUserRepository.findByEmail(email);
    }
}
