package thecrevance.controller;

import thecrevance.dto.UserDto;
import thecrevance.enums.RoleType;
import thecrevance.security.TokenAuthenticationService;
import thecrevance.service.UserService;
import thecrevance.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private UserService userService;
    private TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    public UserController(UserService userService,
                          TokenAuthenticationService tokenAuthenticationService) {
        this.userService = userService;
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @PostMapping(path="/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto user) throws Exception {
        final User registered = userService.saveUser(user, RoleType.USER);
        registered.setExpires(System.currentTimeMillis() + TokenAuthenticationService.TEN_DAYS);
        String token = tokenAuthenticationService.createUserToken(registered);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(TokenAuthenticationService.AUTH_HEADER_NAME, token);
        return new ResponseEntity<>(registered, responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping(path = "/hello")
    public String getHello(){
        return "hello";
    }
}
