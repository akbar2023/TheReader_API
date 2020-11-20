package fr.akbarkhan.mediatheque.service;

import fr.akbarkhan.mediatheque.dto.BookDetailsDto;
import fr.akbarkhan.mediatheque.dto.BookDto;
import fr.akbarkhan.mediatheque.dto.UserBookDto;
import fr.akbarkhan.mediatheque.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    boolean saveBook(BookDto bookDto);

    List<BookDetailsDto> findAllWithCreator();

    List<Book> findByTitle(String title);

    BookDetailsDto findById(Integer id);

    boolean deleteBook(UserBookDto userBookDto);

    boolean updateBook(BookDto bookDto);
}
