package fr.akbarkhan.mediatheque;

import fr.akbarkhan.mediatheque.entity.Book;
import fr.akbarkhan.mediatheque.entity.MyUser;
import fr.akbarkhan.mediatheque.entity.Role;
import fr.akbarkhan.mediatheque.entity.UserBook;
import fr.akbarkhan.mediatheque.repository.BookRepository;
import fr.akbarkhan.mediatheque.repository.RoleRepository;
import fr.akbarkhan.mediatheque.repository.UserBookRepository;
import fr.akbarkhan.mediatheque.repository.UserRepository;
import fr.akbarkhan.mediatheque.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.*;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class MediathequeApplication {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(MediathequeApplication.class, args);
    }

    @Bean
    @Transactional
    CommandLineRunner start(RoleRepository roleRepository, UserRepository userRepository, BookRepository bookRepository, UserBookRepository userBookRepository) {
        return args -> {
            roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("USER"));
            Collection<Role> roles = new ArrayList<Role>();
            roles.add(roleRepository.findByRole("USER").orElse(null));
            List<MyUser> myUsers = List.of(
                    new MyUser(null, "Laurent", "LAM", "laurent@email.com",
                            passwordEncoder.encode("password"), roles, null, true),
                    new MyUser(null, "laura", "NEP", "laura@email.com",
                            passwordEncoder.encode("password"), roles, null, true),
                    new MyUser(null, "Satsuki", "Kusakabe", "satsuki@email.com",
                            passwordEncoder.encode("password"), roles, null, true)
            );

            userRepository.saveAll(myUsers);


            MyUser laurent = userService.findByUsername("laurent@email.com");
            MyUser laura = userService.findByUsername("laura@email.com");
            MyUser satsuki = userService.findByUsername("satsuki@email.com");

            for (int x = 2; x < 10; x++) {
                Book book = bookRepository.save(new Book(null, "The Reader " + x, "Comedy", "Jean Honoré Fragonard", 2012, "The little pretty girl is reading", satsuki, null));
                userBookRepository.save(new UserBook(book, satsuki, false, false));
                userBookRepository.save(new UserBook(book, laura, false, false));
                userBookRepository.save(new UserBook(book, laurent, true, true));
            }
        };
    }

}

