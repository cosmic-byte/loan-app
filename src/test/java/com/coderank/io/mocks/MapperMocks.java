package com.coderank.io.mocks;

import com.coderank.io.dto.UserDto;
import com.coderank.io.mapper.UserDtoMapper;
import com.coderank.io.stubs.TestStubs;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class MapperMocks {

    public void initMocks(UserDtoMapper userDtoMapper){
        when(userDtoMapper.toUser(any(UserDto.class))).thenReturn(TestStubs.generateUser());
    }

}
