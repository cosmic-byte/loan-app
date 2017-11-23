package thecrevance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thecrevance.dto.AccountCredentials;
import thecrevance.dto.UserRole;
import thecrevance.security.TokenAuthenticationService;
import thecrevance.service.UserService;

/**
 * Created by Greg on 10/6/17.
 */
@RestController
public class LoginController {
    private final TokenAuthenticationService tokenAuthenticationService;
    private final UserService userService;

    @Autowired
    public LoginController(TokenAuthenticationService tokenAuthenticationService,
                           UserService userService) {
        this.tokenAuthenticationService = tokenAuthenticationService;
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> authenticationRequest(@RequestBody AccountCredentials accountDetails) {
        String token = tokenAuthenticationService.createToken(accountDetails.getEmail(), accountDetails.getPassword());
        if (token == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        String roles = userService.getUserRoles(accountDetails.getEmail());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(TokenAuthenticationService.TOKEN_HEADER, token);
        return new ResponseEntity<>(new UserRole(token, roles),responseHeaders, HttpStatus.OK);
    }

}
