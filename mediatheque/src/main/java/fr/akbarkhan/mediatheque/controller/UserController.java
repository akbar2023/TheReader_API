package fr.akbarkhan.mediatheque.controller;

        import fr.akbarkhan.mediatheque.dto.UserDto;
        import fr.akbarkhan.mediatheque.entity.User;
        import fr.akbarkhan.mediatheque.exception.UserExceptionHandler;
        import fr.akbarkhan.mediatheque.exception.UserNotFoundException;
        import fr.akbarkhan.mediatheque.service.UserService;
        import java.util.List;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.CrossOrigin;
        import org.springframework.web.bind.annotation.ExceptionHandler;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String createUser(@RequestBody UserDto userDto) {
        User user = new User(userDto.getUsername(), userDto.getPassword());
        userService.saveUser(user);
        return "Sign Up success for user " + user.getUsername() + "!";
    }

    @GetMapping
    public String userLogin(@RequestBody UserDto userDto) {

        User user =  userService.findByUsername(userDto.getUsername());

        if (user == null) {
            throw new UserNotFoundException("User not found!");
        }
        else if(user.getPassword().equals(userDto.getPassword())) {
            return "Login success!";
        }
        else{
            return "Password incorrect!";
        }
    }
}
