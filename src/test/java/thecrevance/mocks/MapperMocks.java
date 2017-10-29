package thecrevance.mocks;

import thecrevance.dto.PreUser;
import thecrevance.mapper.UserDtoMapper;
import thecrevance.stubs.TestStubs;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class MapperMocks {

    public void initMocks(UserDtoMapper userDtoMapper){
        when(userDtoMapper.toUser(any(PreUser.class))).thenReturn(TestStubs.generateUser());
    }

}
