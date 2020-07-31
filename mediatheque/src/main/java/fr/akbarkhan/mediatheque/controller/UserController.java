package fr.akbarkhan.mediatheque.controller;

import fr.akbarkhan.mediatheque.dto.UserDto;
import fr.akbarkhan.mediatheque.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String createUser(@Valid @RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        return "Sign Up success for userDto " + userDto.getFirstName() + "!";
    }

//    @GetMapping
//    public String userLogin(@RequestBody UserDto userDto) {
//
//        User user =  userService.findByUsername(userDto.getUsername());
//
//        if (user == null) {
//            throw new UserNotFoundException("User not found!");
//        }
//        else if(user.getPassword().equals(userDto.getPassword())) {
//            return "Login success!";
//        }
//        else{
//            return "Password incorrect!";
//        }
//    }
}
