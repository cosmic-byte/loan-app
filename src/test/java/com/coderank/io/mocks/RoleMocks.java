package com.coderank.io.mocks;

import com.coderank.io.repository.RoleRepository;
import com.coderank.io.stubs.TestStubs;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class RoleMocks {

    public void initMocks(RoleRepository roleRepository){
        when(roleRepository.findByName(anyString())).thenReturn(TestStubs.generateOptionalRole());
    }
}
