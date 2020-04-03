package fr.akbarkhan.mediatheque.service;

import fr.akbarkhan.mediatheque.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    void saveBook(Book book);

    List<Book> findAll();

    Iterable<Book> findByTitle(String title);

    Optional<Book> findById(int id);
}
