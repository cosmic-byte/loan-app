package thecrevance.mocks;

import thecrevance.repository.RoleRepository;
import thecrevance.stubs.TestStubs;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class RoleMocks {

    public void initMocks(RoleRepository roleRepository){
        when(roleRepository.findByName(anyString())).thenReturn(TestStubs.generateOptionalRole());
    }
}
