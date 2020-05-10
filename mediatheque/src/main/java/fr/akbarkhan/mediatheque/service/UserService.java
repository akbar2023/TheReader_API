package fr.akbarkhan.mediatheque.service;

import fr.akbarkhan.mediatheque.entity.User;
import java.util.List;

public interface UserService {

    User saveUser(User user);

    User findByUsername(String username);
}
