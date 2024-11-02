package com.example.sandav.domain.port;

import com.example.sandav.domain.model.User;

public interface IUserRepository {
    User save(User user);

    User findByEmail(String email);
}
