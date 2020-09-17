package fr.akbarkhan.mediatheque.auth;

import fr.akbarkhan.mediatheque.entity.MyUser;
import fr.akbarkhan.mediatheque.exception.UserNotFoundException;
import fr.akbarkhan.mediatheque.repository.UserRepository;
import fr.akbarkhan.mediatheque.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyUser myUser = userService.findByUsername(username);

        if (myUser != null) {
            Collection<GrantedAuthority> authorities = new ArrayList<>();

            myUser.getRoles().forEach(r -> {
                authorities.add(new SimpleGrantedAuthority(r.getRole()));
            });
            return new User(myUser.getEmail(), myUser.getPassword(), authorities);
        }
        throw new UserNotFoundException(String.format("user %s not found", username));

    }
}
