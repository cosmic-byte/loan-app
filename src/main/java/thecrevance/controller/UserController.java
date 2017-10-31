package thecrevance.controller;

import org.springframework.data.domain.PageRequest;
import thecrevance.dto.PageData;
import thecrevance.dto.PreUser;
import thecrevance.dto.UserDto;
import thecrevance.enums.RoleType;
import thecrevance.security.TokenAuthenticationService;
import thecrevance.service.UserService;
import thecrevance.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService,
                          TokenAuthenticationService tokenAuthenticationService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method= RequestMethod.POST)
    public ResponseEntity<?> createNewUser(@RequestBody PreUser user) throws Exception {
        User registeredUser = this.userService.saveUser(user, RoleType.USER);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @RequestMapping(method= RequestMethod.GET)
    public PageData<UserDto> getAllRegisteredUsers(@RequestParam(value = "pageNumber", defaultValue = "1")
                                                        Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber-1, 10);
        return this.userService.getAllUsers(pageRequest);
    }

    @RequestMapping(value = "/{userId}", method= RequestMethod.GET)
    public UserDto getUserById(@PathVariable final Long userId) {
        return this.userService.getUserById(userId);
    }

    @RequestMapping(value = "/{userId}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) {
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}/makeAdmin")
    public User makeAdmin(@PathVariable final Long userId){
        return this.userService.changeRole(userId,RoleType.ADMIN);
    }
}
