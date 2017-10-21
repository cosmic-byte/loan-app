package com.coderank.io.mocks;

import com.coderank.io.repository.UserRepository;
import com.coderank.io.stubs.TestStubs;
import com.coderank.io.model.User;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class UserMocks {
    public void initMocks(UserRepository userRepository){
        when(userRepository.saveAndFlush(any(User.class))).thenReturn(TestStubs.generateUser());
    }
}
