package thecrevance.stubs;

import thecrevance.dto.PreUser;
import thecrevance.enums.RoleType;
import thecrevance.model.Role;
import thecrevance.model.User;

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

    public static PreUser generateUserDto(){
        return new PreUser("Patrick", "Emmanuel",
                "Password", "email@email.com");
    }

    public static Role generateRole(){
        return new Role(RoleType.USER.name());
    }
    public static Optional<Role> generateOptionalRole(){
        return Optional.of(new Role(RoleType.USER.name()));
    }
}
