package com.coderank.io.service;

import com.coderank.io.dto.UserDto;
import com.coderank.io.enums.RoleType;
import com.coderank.io.model.User;


public interface UserService {

    User saveUser(UserDto userDto, RoleType roleType);
}
