package com.example.sandav.infrastructure.mapper;

import com.example.sandav.domain.model.User;
import com.example.sandav.infrastructure.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings(
            {
                    @Mapping(source = "id", target = "id"),
                    @Mapping(source = "email", target = "email"),
                    @Mapping(source = "password", target = "password"),
                    @Mapping(source = "rol", target = "rol")
            }
    )
    User toUser(UserEntity userEntity);

    @InheritInverseConfiguration
    UserEntity toUserEntity(User user);
}
