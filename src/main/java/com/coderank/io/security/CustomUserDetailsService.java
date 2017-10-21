package com.coderank.io.security;

import com.coderank.io.model.User;
import com.coderank.io.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CustomUserDetailsService implements CustomUserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.getByEmailAndDeletedFalse(username);
        optionalUser.ifPresent(this::reflectLogin);
        throw new UsernameNotFoundException("User with  '" + username + "' email not found.");
    }

    private User reflectLogin(User user) {
        //user.setLastLogin(LocalDateTime.now());
        return userRepository.saveAndFlush(user);
    }
}
