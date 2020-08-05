package fr.akbarkhan.mediatheque.service;

import fr.akbarkhan.mediatheque.dto.UserDto;
import fr.akbarkhan.mediatheque.entity.User;

public interface UserService {

    void saveUser(UserDto user);

    User findByUsername(String username);
}
