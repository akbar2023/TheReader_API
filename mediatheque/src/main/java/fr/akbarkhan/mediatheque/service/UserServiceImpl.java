package fr.akbarkhan.mediatheque.service;

import fr.akbarkhan.mediatheque.dto.ConnectedUserDto;
import fr.akbarkhan.mediatheque.dto.UserBookDto;
import fr.akbarkhan.mediatheque.dto.UserDto;
import fr.akbarkhan.mediatheque.dto.UserRegisterDto;
import fr.akbarkhan.mediatheque.entity.Book;
import fr.akbarkhan.mediatheque.entity.MyUser;
import fr.akbarkhan.mediatheque.entity.Role;
import fr.akbarkhan.mediatheque.repository.BookRepository;
import fr.akbarkhan.mediatheque.repository.RoleRepository;
import fr.akbarkhan.mediatheque.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
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

        Optional<MyUser> userDB = userRepository.findByEmail(registerDto.getEmail());
            if(userDB.isEmpty()) {
                MyUser user = new MyUser();
                user.setFirstName(registerDto.getFirstName());
                user.setLastName(registerDto.getLastName());
                user.setEmail(registerDto.getEmail());
                user.setPassword(passwordEncoder.encode(registerDto.getPassword())); // pwd encoded
                Collection<Role> roles = new ArrayList<Role>();
                Role role = roleRepository.findByRole("USER").orElse(null);
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
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            Collection<Role> roles = new ArrayList<Role>();
            Role role = roleRepository.findByRole(userDto.getRole()).orElse(null);
            roles.add(role);
            user.setRoles(roles);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean addUserBook(UserBookDto userBookDto) {
        MyUser user = userRepository.findById(userBookDto.getUserId()).orElse(null);
        Book bookToAdd = bookRepository.findById(userBookDto.getBookId()).orElse(null);
        if(user != null && bookToAdd != null) {
            List<Book> books = user.getBookList();
            books.add(bookToAdd);
            user.setBookList(books);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public List<Book> findUserBooks(Integer userId) {
        MyUser user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            List<Book> books = user.getBookList();

            List<Book> list = books.stream().map(book -> {

                MyUser creator = new MyUser();
                creator.setFirstName(book.getCreator().getFirstName());
                creator.setLastName(book.getCreator().getLastName());
                creator.setEmail(book.getCreator().getEmail());

                return new Book(book.getId(),
                        book.getTitle(),
                        book.getGenre(),
                        book.getAuthor(),
                        book.getYear(),
                        book.getSummary(),
                        creator, null);
            }).collect(Collectors.toList());
            return list;
        }
        return null;
    }
}
