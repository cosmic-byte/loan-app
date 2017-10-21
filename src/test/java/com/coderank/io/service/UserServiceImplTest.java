package com.coderank.io.service;

import com.coderank.io.mocks.EnvironmentMocks;
import com.coderank.io.mocks.MapperMocks;
import com.coderank.io.dto.UserDto;
import com.coderank.io.enums.RoleType;
import com.coderank.io.mapper.UserDtoMapper;
import com.coderank.io.mocks.RoleMocks;
import com.coderank.io.mocks.UserMocks;
import com.coderank.io.model.User;
import com.coderank.io.repository.RoleRepository;
import com.coderank.io.repository.UserRepository;
import com.coderank.io.stubs.TestStubs;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    //dependencies
    @MockBean
    private Environment environment;
    @MockBean
    private RoleRepository roleRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserDtoMapper userDtoMapper;

    //system under test.
    private UserService userService;

    //Mocks.
    private MapperMocks mapperMocks = new MapperMocks();
    private RoleMocks roleMocks = new RoleMocks();
    private UserMocks userMocks = new UserMocks();
    private EnvironmentMocks environmentMocks = new EnvironmentMocks();

    @Before
    public void setUp() throws Exception {
        mapperMocks.initMocks(userDtoMapper);
        environmentMocks.initMocks(environment);
        roleMocks.initMocks(roleRepository);
        userMocks.initMocks(userRepository);
        userService = new UserServiceImpl(environment, roleRepository, userRepository, userDtoMapper);
    }

    @Test
    public void saveUserShouldSave() throws Exception {
        UserDto userDto = TestStubs.generateUserDto();
        User user = userService.saveUser(userDto, RoleType.USER);

        assertThat(user).isNotNull();
    }

}