package fr.akbarkhan.mediatheque.service;

import fr.akbarkhan.mediatheque.dto.UserDto;
import fr.akbarkhan.mediatheque.entity.User;
import java.util.List;

public interface UserService {

    User saveUser(UserDto user);

    User findByUsername(String username);
}
