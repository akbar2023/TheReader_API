package fr.akbarkhan.mediatheque.service;

import fr.akbarkhan.mediatheque.dto.*;
import fr.akbarkhan.mediatheque.entity.Book;
import fr.akbarkhan.mediatheque.entity.MyUser;

import java.util.List;
import java.util.Set;

public interface UserService {

    boolean saveUser(UserRegisterDto user);

    MyUser findByUsername(String username);

    ConnectedUserDto findById(Integer id);

    boolean updateUser(UserDto userDto, int userId);

    boolean addBookToUserList(Integer bookId, Integer userId);

    Set<BookDetailsDto> findUserBooks(Integer userId);

    boolean removeBookFromUserList(Integer bookId, Integer userId);
}
