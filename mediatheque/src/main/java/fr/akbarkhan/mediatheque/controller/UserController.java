package fr.akbarkhan.mediatheque.controller;

import fr.akbarkhan.mediatheque.controller.Reusable.Methods;
import fr.akbarkhan.mediatheque.dto.*;
import fr.akbarkhan.mediatheque.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private Methods methods;

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRegisterDto registerDto) {
        return methods.getResponseEntity(userService.saveUser(registerDto));
    }

    // todo : improve security to let a user update his account info
    @PutMapping("update")
    @PreAuthorize("hasAnyAuthority('ADMIN, USER')")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDto userDto, Principal principal) {
        Integer userId = methods.getUserIdFromToken(principal);
        return methods.getResponseEntity(userService.updateUser(userDto, userId));
    }

    @GetMapping("info")
    @PreAuthorize("hasAnyAuthority('ADMIN,USER')")
    public ResponseEntity<?> getUserDetails(Principal principal) {
        Integer userId = methods.getUserIdFromToken(principal);
        ConnectedUserDto byId = userService.findById(userId);
        if (byId != null) {
            return ResponseEntity.status(HttpStatus.OK).body(byId);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    // todo : Implement User deletion
}
