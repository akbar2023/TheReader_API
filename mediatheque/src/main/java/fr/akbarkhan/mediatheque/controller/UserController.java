package fr.akbarkhan.mediatheque.controller;

import fr.akbarkhan.mediatheque.dto.*;
import fr.akbarkhan.mediatheque.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRegisterDto registerDto) {
        if(userService.saveUser(registerDto)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
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

    @PostMapping("/add-to-list/{bookId}")
    @PreAuthorize("hasAnyAuthority('ADMIN,USER')")
    public ResponseEntity<?> addBookToList(@PathVariable("bookId") Integer bookId, Principal principal) {
        Integer userId = getUserIdFromToken(principal);
        if (userService.addBookToUserList(bookId, userId)) {
            return ResponseEntity.status(HttpStatus.OK).body("Book added");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book add failed");
        }
    }

    private Integer getUserIdFromToken(Principal principal) {
        return Integer.parseInt(principal.getName());
    }

    @GetMapping("{userId}/books")
    @PreAuthorize("hasAnyAuthority('ADMIN,USER')")
    public Set<BookDetailsDto> getUserBookList(@PathVariable("userId") Integer userId) {
        return userService.findUserBooks(userId);
    }

    @PutMapping("/remove-book/{bookId}")
    @PreAuthorize("hasAnyAuthority('ADMIN,USER')")
    public ResponseEntity<?> updateBookList(@PathVariable("bookId") Integer bookId, Principal principal) {
        Integer userId = getUserIdFromToken(principal);
        if (userService.removeBookFromUserList(bookId, userId)) {
            return ResponseEntity.status(HttpStatus.OK).body("book list update ok");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update book list failed");
        }
    }
}
