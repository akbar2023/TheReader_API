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
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRegisterDto registerDto) {
        if(userService.saveUser(registerDto)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // todo : improve security to let a user update his account info
    @PutMapping("update")
    @PreAuthorize("hasAnyAuthority('ADMIN, USER')")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDto userDto, Principal principal) {
        Integer userId = getUserIdFromToken(principal);
        if (userService.updateUser(userDto, userId)) {
            String message = String.format("user %s updated", userDto.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("info")
    @PreAuthorize("hasAnyAuthority('ADMIN,USER')")
    public ResponseEntity<?> getUserDetails(Principal principal) {
        Integer userId = getUserIdFromToken(principal);
        ConnectedUserDto byId = userService.findById(userId);
        if (byId != null) {
            return ResponseEntity.status(HttpStatus.OK).body(byId);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping("add-to-list/{bookId}")
    @PreAuthorize("hasAnyAuthority('ADMIN,USER')")
    public ResponseEntity<?> addBookToList(@PathVariable("bookId") Integer bookId, Principal principal) {
        Integer userId = getUserIdFromToken(principal);
        if (userService.addBookToList(bookId, userId)) {
            return ResponseEntity.status(HttpStatus.OK).body("Book added");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book add failed");
        }
    }

    // todo: remove
    @GetMapping("books")
    @PreAuthorize("hasAnyAuthority('ADMIN,USER')")
    public Set<BookDetailsDto> getUsersBookList(Principal principal) {
        Integer userId = getUserIdFromToken(principal);
        return userService.getBookList(userId);
    }

    @PutMapping("remove-book/{bookId}")
    @PreAuthorize("hasAnyAuthority('ADMIN,USER')")
    public ResponseEntity<?> updateBookList(@PathVariable("bookId") Integer bookId, Principal principal) {
        Integer userId = getUserIdFromToken(principal);
        if (userService.removeBookFromList(bookId, userId)) {
            return ResponseEntity.status(HttpStatus.OK).body("book list update ok");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    private Integer getUserIdFromToken(Principal principal) {
        return Integer.parseInt(principal.getName());
    }
}
