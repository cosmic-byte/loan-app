package thecrevance.security;

import thecrevance.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserService extends UserDetailsService {
    User loadUserByUsername(String username);
}
