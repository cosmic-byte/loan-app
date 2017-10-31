package thecrevance.service;

import org.springframework.data.domain.Pageable;
import thecrevance.dto.PageData;
import thecrevance.dto.PreUser;
import thecrevance.dto.UserDto;
import thecrevance.enums.RoleType;
import thecrevance.model.User;


public interface UserService {

    User saveUser(PreUser preUser, RoleType roleType);

    String getUserRoles(String email);

    PageData<UserDto> getAllUsers(Pageable pageable);

    User changeRole(Long userId, RoleType roleType);

    UserDto getUserById(Long id);

    void deleteUser(Long userId);
}
