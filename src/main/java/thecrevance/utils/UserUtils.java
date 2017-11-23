package thecrevance.utils;

/**
 * Created by Greg on 10/31/17.
 */

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class UserUtils {
    public static UserDetails getLoggedInUser(){
        Authentication authentication =	SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetails)authentication.getPrincipal());
    }
}
