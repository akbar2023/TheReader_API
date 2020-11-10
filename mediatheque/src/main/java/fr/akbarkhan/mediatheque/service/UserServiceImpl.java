package fr.akbarkhan.mediatheque.service;

import fr.akbarkhan.mediatheque.dto.*;
import fr.akbarkhan.mediatheque.entity.Book;
import fr.akbarkhan.mediatheque.entity.MyUser;
import fr.akbarkhan.mediatheque.entity.Role;
import fr.akbarkhan.mediatheque.repository.BookRepository;
import fr.akbarkhan.mediatheque.repository.RoleRepository;
import fr.akbarkhan.mediatheque.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean saveUser(UserRegisterDto registerDto) {

        if (!userExists(registerDto)) {
            MyUser user = new MyUser();
            user.setFirstName(registerDto.getFirstName());
            user.setLastName(registerDto.getLastName());
            user.setEmail(registerDto.getEmail());
            user.setPassword(getEncoded(registerDto.getPassword())); // pwd encoded
            Collection<Role> roles = new ArrayList<Role>();
            Role role = getRoleUser("USER");
            roles.add(role);
            user.setRoles(roles);
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public MyUser findByUsername(String username) {
        Optional<MyUser> user = userRepository.findByEmail(username);
        return user.orElse(null);
    }

    @Override
    public ConnectedUserDto findByEmail(String username) {
        Optional<MyUser> user = userRepository.findByEmail(username);
        ConnectedUserDto detailsDto = new ConnectedUserDto();
        if (user.isPresent()) {
            detailsDto.setId(user.get().getId());
            detailsDto.setFirstName(user.get().getFirstName());
            detailsDto.setLastName(user.get().getLastName());
            detailsDto.setEmail(user.get().getEmail());
            return detailsDto;
        } else {
            return null;
        }
    }

    @Override
    public boolean updateUser(UserDto userDto, int userId) {
        MyUser user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setPassword(getEncoded(userDto.getPassword()));
            Collection<Role> roles = new ArrayList<Role>();
            Role role = getRoleUser(userDto.getRole());
            roles.add(role);
            user.setRoles(roles);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean addBookToUserList(UserBookDto userBookDto) {
        MyUser user = userRepository.findById(userBookDto.getUserId()).orElse(null);
        Book bookToAdd = bookRepository.findById(userBookDto.getBookId()).orElse(null);
        if (user != null && bookToAdd != null) {
            List<Book> bookList = user.getBookList();
            if (!bookList.contains(bookToAdd)) {
                bookList.add(bookToAdd);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeBookFromUserList(UserBookDto userBookDto) {
        MyUser user = userRepository.findById(userBookDto.getUserId()).orElse(null);
        if (user != null) {
            List<Book> oldList = user.getBookList();
            List<Book> newList = oldList.stream()
                    .filter(book -> !book.getId().equals(userBookDto.getBookId()))
                    .collect(Collectors.toList());
            user.setBookList(newList);
            userRepository.save(user);
            return true;
        }
            return false;
    }

    @Override
    public Set<BookDetailsDto> findUserBooks(Integer userId) {
        MyUser user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            List<Book> books = user.getBookList();

            return books.stream().map(book -> {

                MyUser creator = book.getCreator();
                CreatorDto creatorDto = new CreatorDto(
                        creator.getFirstName(),
                        creator.getLastName(),
                        creator.getEmail());

                return new BookDetailsDto(book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getGenre(),
                        book.getSummary(),
                        book.getYear(),
                        creatorDto);
            }).collect(Collectors.toSet());
        }
        return null;
    }

    private String getEncoded(String password) {
        return passwordEncoder.encode(password);
    }

    private Role getRoleUser(String user) {
        return roleRepository.findByRole(user).orElse(null);
    }

    private boolean userExists(UserRegisterDto registerDto) {
        return userRepository.findByEmail(registerDto.getEmail()).isPresent();
    }
}
