package co.partha.mappers;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;

import org.mapstruct.Mapper;

import co.partha.entities.UserEntity;
import co.partha.models.User;

@Mapper(componentModel = "cdi")
@ApplicationScoped
@Default
public interface UserMapper {
    User toUser(UserEntity userEntity);
    UserEntity toUserEntity(User user);
}
