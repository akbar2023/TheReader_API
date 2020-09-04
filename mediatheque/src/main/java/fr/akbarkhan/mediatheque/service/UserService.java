package fr.akbarkhan.mediatheque.service;

import fr.akbarkhan.mediatheque.dto.UserDto;
import fr.akbarkhan.mediatheque.dto.UserRegisterDto;
import fr.akbarkhan.mediatheque.entity.MyUser;

public interface UserService {

    boolean saveUser(UserRegisterDto user);

    MyUser findByUsername(String username);

    boolean updateUser(UserDto userDto, int id);
}
