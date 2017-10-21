package thecrevance.service;

import thecrevance.dto.UserDto;
import thecrevance.enums.RoleType;
import thecrevance.model.User;


public interface UserService {

    User saveUser(UserDto userDto, RoleType roleType);
}
