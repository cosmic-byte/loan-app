package thecrevance.mapper;

import thecrevance.model.PreUser;
import thecrevance.model.User;
import thecrevance.stubs.TestStubs;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.modelmapper.ModelMapper;


public class UserDtoMapperTest {

    private ModelMapper mapper = new ModelMapper();
    UserDtoMapper userDtoMapper = new UserDtoMapper(mapper);
    private User user;
    private PreUser preUser;

    @Before
    public void setUp() throws Exception {
        user = TestStubs.generateUser();
        preUser = TestStubs.generateUserDto();
    }

    @Test
    public void toUserDtoWhenUserEmailAndDtoEmailAreSameShouldReturnTrue() throws Exception {
        PreUser preUser =  userDtoMapper.toPreUser(user);

        assertThat(preUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void toUserDtoWhenUserPasswordAndDtoPasswordAreSameShouldReturnTrue() throws Exception {
        PreUser preUser =  userDtoMapper.toPreUser(user);

        assertThat(preUser.getPassword()).isEqualTo(user.getPassword());
    }
    @Test
    public void toUserDtoWhenUserFirstnameAndDtoFirstnameAreSameShouldReturnTrue() throws Exception {
        PreUser preUser =  userDtoMapper.toPreUser(user);

        assertThat(preUser.getFirstname()).isEqualTo(user.getFirstname());
    }

    @Test
    public void toUserDtoWhenUserLastnameAndDtoLastnameAreSameShouldReturnTrue() throws Exception {
        PreUser preUser =  userDtoMapper.toPreUser(user);

        assertThat(preUser.getLastname()).isEqualTo(user.getLastname());
    }

    @Test
    public void toUserWhenUserEmailAndDtoEmailAreSameShouldReturnTrue() throws Exception {
        User user =  userDtoMapper.toUser(preUser);

        assertThat(user.getEmail()).isEqualTo(preUser.getEmail());
    }

    @Test
    public void toUserWhenUserFirstnameAndDtoFirstnameAreSameShouldReturnTrue() throws Exception {
        User user =  userDtoMapper.toUser(preUser);

        assertThat(user.getFirstname()).isEqualTo(preUser.getFirstname());
    }

    @Test
    public void toUserWhenUserLastnameAndDtoLastnameAreSameShouldReturnTrue() throws Exception {
        User user =  userDtoMapper.toUser(preUser);

        assertThat(user.getLastname()).isEqualTo(preUser.getLastname());
    }

    @Test
    public void toUserWhenUserPasswordAndDtoPasswordAreSameShouldReturnTrue() throws Exception {
        User user =  userDtoMapper.toUser(preUser);

        assertThat(user.getPassword()).isEqualTo(preUser.getPassword());
    }


}