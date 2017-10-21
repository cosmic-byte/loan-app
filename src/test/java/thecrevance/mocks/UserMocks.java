package thecrevance.mocks;

import thecrevance.repository.UserRepository;
import thecrevance.stubs.TestStubs;
import thecrevance.model.User;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class UserMocks {
    public void initMocks(UserRepository userRepository){
        when(userRepository.saveAndFlush(any(User.class))).thenReturn(TestStubs.generateUser());
    }
}
