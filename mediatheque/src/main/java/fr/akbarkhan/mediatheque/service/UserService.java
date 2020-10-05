package fr.akbarkhan.mediatheque.service;

import fr.akbarkhan.mediatheque.dto.*;
import fr.akbarkhan.mediatheque.entity.Book;
import fr.akbarkhan.mediatheque.entity.MyUser;

import java.util.List;
import java.util.Set;

public interface UserService {

    boolean saveUser(UserRegisterDto user);

    MyUser findByUsername(String username);

    ConnectedUserDto findByEmail(String email);

    boolean updateUser(UserDto userDto, int id);

    boolean addBookToUserList(UserBookDto userBookDto);

    Set<BookDetailsDto> findUserBooks(Integer userId);

    boolean removeBookFromUserList(UserBookDto userBookDto);
}
