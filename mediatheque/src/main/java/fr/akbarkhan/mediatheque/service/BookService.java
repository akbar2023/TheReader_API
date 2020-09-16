package fr.akbarkhan.mediatheque.service;

import fr.akbarkhan.mediatheque.dto.BookDetailsDto;
import fr.akbarkhan.mediatheque.dto.BookDto;
import fr.akbarkhan.mediatheque.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book saveBook(BookDto bookDto);

    List<BookDetailsDto> findAllWithCreator();

    List<Book> findByTitle(String title);

    Optional<Book> findById(int id);

    void deleteBook(int id);

    void updateBook(BookDto bookDto, Integer id);
}
