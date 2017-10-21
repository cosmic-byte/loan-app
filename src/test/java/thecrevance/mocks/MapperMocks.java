package thecrevance.mocks;

import thecrevance.dto.UserDto;
import thecrevance.mapper.UserDtoMapper;
import thecrevance.stubs.TestStubs;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class MapperMocks {

    public void initMocks(UserDtoMapper userDtoMapper){
        when(userDtoMapper.toUser(any(UserDto.class))).thenReturn(TestStubs.generateUser());
    }

}
