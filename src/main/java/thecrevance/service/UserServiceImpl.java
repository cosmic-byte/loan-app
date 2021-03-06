package thecrevance.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import thecrevance.dto.PageData;
import thecrevance.dto.UserDto;
import thecrevance.exceptions.EntityAlreadyExistException;
import thecrevance.exceptions.UserNotInDBException;
import thecrevance.model.*;
import thecrevance.enums.RoleType;
import thecrevance.mapper.UserDtoMapper;
import thecrevance.repository.RoleRepository;
import thecrevance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService{
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private UserDtoMapper userDtoMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(RoleRepository roleRepository,
                           UserRepository userRepository, UserDtoMapper userDtoMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User saveUser(PreUser preUser, RoleType roleType) {
        User check = userRepository.getByEmailAndDeletedFalse(preUser.getEmail());
        if(check != null){
            throw new EntityAlreadyExistException(preUser.getEmail());
        }
        User user = userDtoMapper.toUser(preUser);
        encodeAndSetUserPassword(user);
        return setUserRole(user, roleType);
    }

    @Override
    public String getUserRoles(String email) {
        User user = userRepository.getByEmailAndDeletedFalse(email);
        String roles = user.getRole().getName();
        return roles.trim();
    }

    @Override
    public PageData<UserDto> getAllUsers(Pageable pageable){
        Page<User> users = this.userRepository.findByDeletedFalseOrderByFirstnameAsc(pageable);
        List<UserDto> userDtos = users.getContent().stream().map(x -> userDtoMapper.toUserDto(x)).collect(Collectors.toList());
        Page<UserDto> page = new PageImpl(userDtos,pageable,users.getTotalElements());
        return PageData.getDataFromPage(page);
    }

    @Override
    public User changeRole(Long userId, RoleType roleType){
        User user = userRepository.findOne(userId);
        return this.setUserRole(user,roleType);
    }

    @Override
    public User getUserById(Long id){
        return this.userRepository.findOne(id);
    }

    @Override
    public void deleteUser(Long userId){
        User user = this.userRepository.findOne(userId);
        if(user == null){
            throw new EntityNotFoundException("No user with Id = "+userId+" was found");
        }else{
            user.setDeleted(true);
            userRepository.save(user);
        }
    }

    @Override
    public User updatePersonalInfo(String email, PersonalInfo personalInfo) throws UserNotInDBException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotInDBException("User with email address could not be found in db");
        }
        user.setPersonalInfo(personalInfo);
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User updateBankDetails(String email, UserBankDetails userBankDetails) throws UserNotInDBException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotInDBException("User with email address could not be found in db");
        }
        user.setUserBankDetails(userBankDetails);
        return userRepository.saveAndFlush(user);
    }

    private void encodeAndSetUserPassword(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword().trim()));
    }

    private User setUserRole(User user, RoleType roleType){
        Optional<Role> optionalRole = roleRepository.findByName(roleType.name());
        optionalRole.ifPresent(user::setRole);
        return userRepository.saveAndFlush(user);
    }
}
