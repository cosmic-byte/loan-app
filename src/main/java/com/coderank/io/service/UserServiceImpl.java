package com.coderank.io.service;

import com.coderank.io.exceptions.EntityAlreadyExistException;
import com.coderank.io.model.Role;
import com.coderank.io.constants.EnvironmentConstants;
import com.coderank.io.dto.UserDto;
import com.coderank.io.enums.RoleType;
import com.coderank.io.mapper.UserDtoMapper;
import com.coderank.io.model.User;
import com.coderank.io.repository.RoleRepository;
import com.coderank.io.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{
    private Environment environment;
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private UserDtoMapper userDtoMapper;

    @Autowired
    public UserServiceImpl(Environment environment, RoleRepository roleRepository,
                           UserRepository userRepository, UserDtoMapper userDtoMapper) {
        this.environment = environment;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
    }

    @Override
    public User saveUser(UserDto userDto, RoleType roleType) {
        Optional<User> check = userRepository.getByEmailAndDeletedFalse(userDto.getEmail());
        if(check != null){
            throw new EntityAlreadyExistException(userDto.getEmail());
        }
        User user = userDtoMapper.toUser(userDto);
        encodeAndSetUserPassword(user);
        return setUserRole(user, roleType);
    }

    private void encodeAndSetUserPassword(User user){
        String passwordKey = environment.getProperty(EnvironmentConstants.PASSWORD_KEY);
        StandardPasswordEncoder encoder = new StandardPasswordEncoder(passwordKey);
        user.setPassword(encoder.encode(user.getPassword().trim()));
    }

    private User setUserRole(User user, RoleType roleType){
        Optional<Role> optionalRole = roleRepository.findByName(roleType.name());
        optionalRole.ifPresent(user::setRole);
        return userRepository.saveAndFlush(user);
    }
}
