package com.example.sandav.infrastructure.adapter;

import com.example.sandav.domain.model.User;
import com.example.sandav.domain.port.IUserRepository;
import com.example.sandav.infrastructure.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserCrudRepository implements IUserRepository {

    private final IUserCrudRepository iUserCrudRepository;
    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        return userMapper.toUser(iUserCrudRepository.save(userMapper.toUserEntity(user)));
    }

    @Override
    public User findByEmail(String email) {
        return this.iUserCrudRepository.findByEmail(email).map(userOp -> userMapper.toUser(userOp)).orElse(null);
    }
}
