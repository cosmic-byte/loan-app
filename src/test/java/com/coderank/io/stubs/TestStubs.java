package com.coderank.io.stubs;

import com.coderank.io.dto.UserDto;
import com.coderank.io.enums.RoleType;
import com.coderank.io.model.Role;
import com.coderank.io.model.User;

import java.util.Optional;

public class TestStubs {

    public static User generateUser(){
        return new User("Patrick", "Emmanuel",
                "Password", "email@email.com", generateRole());
    }
    public static User generateUserWithNoRole(){
        return new User("Patrick", "Emmanuel",
                "Password", "email@email.com");
    }

    public static UserDto generateUserDto(){
        return new UserDto("Patrick", "Emmanuel",
                "Password", "email@email.com");
    }

    public static Role generateRole(){
        return new Role(RoleType.USER.name());
    }
    public static Optional<Role> generateOptionalRole(){
        return Optional.of(new Role(RoleType.USER.name()));
    }
}
