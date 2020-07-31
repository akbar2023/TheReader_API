package fr.akbarkhan.mediatheque.repository;

import fr.akbarkhan.mediatheque.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
