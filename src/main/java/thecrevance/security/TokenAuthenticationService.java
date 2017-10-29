package thecrevance.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Created by Greg on 10/5/17.
 */
@Service
public class TokenAuthenticationService {

    public static final String TOKEN_HEADER = "X-AUTH-TOKEN";
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenHandler tokenHandler;
    @Autowired
    private UserDetailsService userDetailsService;

    public String createToken(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username,
                        password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return tokenHandler.generateToken(userDetails);
    }
}