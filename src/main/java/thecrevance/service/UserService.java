package thecrevance.service;

import org.springframework.data.domain.Pageable;
import thecrevance.dto.PageData;
import thecrevance.exceptions.UserNotInDBException;
import thecrevance.model.PersonalInfo;
import thecrevance.model.PreUser;
import thecrevance.dto.UserDto;
import thecrevance.enums.RoleType;
import thecrevance.model.User;
import thecrevance.model.UserBankDetails;


public interface UserService {

    User saveUser(PreUser preUser, RoleType roleType);

    String getUserRoles(String email);

    PageData<UserDto> getAllUsers(Pageable pageable);

    User changeRole(Long userId, RoleType roleType);

    User getUserById(Long id);

    void deleteUser(Long userId);

    User updatePersonalInfo(String email, PersonalInfo personalInfo) throws UserNotInDBException;

    User updateBankDetails(String email, UserBankDetails userBankDetails) throws UserNotInDBException;
}
