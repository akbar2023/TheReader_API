package fr.akbarkhan.mediatheque.repository;

import fr.akbarkhan.mediatheque.entity.MyUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUser, Integer> {
    Optional<MyUser> findByEmail(String email);
}
