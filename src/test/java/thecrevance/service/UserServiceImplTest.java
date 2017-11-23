package thecrevance.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import thecrevance.mocks.EnvironmentMocks;
import thecrevance.mocks.MapperMocks;
import thecrevance.model.PreUser;
import thecrevance.enums.RoleType;
import thecrevance.mapper.UserDtoMapper;
import thecrevance.mocks.RoleMocks;
import thecrevance.mocks.UserMocks;
import thecrevance.model.User;
import thecrevance.repository.RoleRepository;
import thecrevance.repository.UserRepository;
import thecrevance.stubs.TestStubs;
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
    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    //system under test.
    private UserService userService;

    //Mocks.
    private MapperMocks mapperMocks = new MapperMocks();
    private RoleMocks roleMocks = new RoleMocks();
    private UserMocks userMocks = new UserMocks();
    private EnvironmentMocks environmentMocks = new EnvironmentMocks();

    public UserServiceImplTest() {
    }

    @Before
    public void setUp() throws Exception {
        mapperMocks.initMocks(userDtoMapper);
        roleMocks.initMocks(roleRepository);
        userMocks.initMocks(userRepository);
        userService = new UserServiceImpl(roleRepository, userRepository, userDtoMapper,bCryptPasswordEncoder);
    }

    @Test
    public void saveUserShouldSave() throws Exception {
        PreUser preUser = TestStubs.generateUserDto();
        User user = userService.saveUser(preUser, RoleType.USER);

        assertThat(user).isNotNull();
    }

}