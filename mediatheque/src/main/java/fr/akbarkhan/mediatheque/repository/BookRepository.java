package fr.akbarkhan.mediatheque.repository;

import fr.akbarkhan.mediatheque.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Iterable<Book> findAllByTitle(String title);
}
