package co.partha.services;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import co.partha.entities.UserEntity;
import co.partha.mappers.UserMapper;
import co.partha.models.User;
import co.partha.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class UserService {
    @Inject
    private  UserRepository userRepository;
    @Inject
    private  UserMapper userMapper;

    @Transactional
    public User saveUserToDB(User user) {
        user.setId(null);
        UserEntity userEntity = userMapper.toUserEntity(user);
        userRepository.persist(userEntity);
        return userMapper.toUser(userEntity);
    }

    @Transactional
    public List<User> getAllUsers() {
        List<UserEntity> userEntities = userRepository.listAll();
        return userEntities
            .stream()
            .map(userMapper::toUser)
            .collect(Collectors.toList());
    }

    @Transactional
    public User getUserNameAndEmail(String name, String email) {
        UserEntity userEntity = userRepository.find("name = ?1 and email = ?2", name, email).firstResult();
        return userMapper.toUser(userEntity);
    }

    public Optional<User> getUserNameAndPhone(String name, String phone) {
         Optional<UserEntity> userEntity = userRepository.findUsingNativeQuery("select * from MyUser where name = ?1 and phoneNumber = ?2", 
            name, phone);
            log.info("userEntity is :<{}>", userEntity.isPresent()? userEntity.get(): "not found");
        
        return userEntity.map(userMapper::toUser);

    }

    public User updateUser(User user) {
        UserEntity userEntity = userMapper.toUserEntity(user);
        userRepository.persist(userEntity);
        return userMapper.toUser(userEntity);
    }


}
