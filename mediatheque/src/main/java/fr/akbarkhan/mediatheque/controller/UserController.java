package fr.akbarkhan.mediatheque.controller;

import fr.akbarkhan.mediatheque.dto.UserDto;
import fr.akbarkhan.mediatheque.dto.UserRegisterDto;
import fr.akbarkhan.mediatheque.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String createUser(@Valid @RequestBody UserRegisterDto registerDto) {
        String email = registerDto.getEmail();
        return userService.saveUser(registerDto) ? "Sign Up success" : "User " + email + " already exists";
    }

    // TODO: set access to ADMIN only
//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{userId}")
    public String updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") int userId) {
        userService.updateUser(userDto, userId);
        return String.format("user %s updated", userDto.getEmail());
    }
}
