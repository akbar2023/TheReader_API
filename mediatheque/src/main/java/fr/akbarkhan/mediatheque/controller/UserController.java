package fr.akbarkhan.mediatheque.controller;

import fr.akbarkhan.mediatheque.dto.ConnectedUserDto;
import fr.akbarkhan.mediatheque.dto.UserDto;
import fr.akbarkhan.mediatheque.dto.UserRegisterDto;
import fr.akbarkhan.mediatheque.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PutMapping("/update/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") int userId) {
        if (userService.updateUser(userDto, userId)) {
            return String.format("user %s updated", userDto.getEmail());
        } else {
            return String.format("user %s update error", userDto.getEmail());
        }
    }

    @GetMapping("/{email}")
    @PreAuthorize("hasAnyAuthority('ADMIN,USER')")
    public ConnectedUserDto getUserDetails(@PathVariable("email") String email) {
        return userService.findByEmail(email);
    }
}
