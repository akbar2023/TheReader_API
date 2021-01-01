package fr.akbarkhan.mediatheque.service;

import fr.akbarkhan.mediatheque.dto.BookDetailsDto;
import fr.akbarkhan.mediatheque.dto.BookDto;
import fr.akbarkhan.mediatheque.dto.BookLiteDto;

import java.util.List;

public interface BookService {
    boolean saveBook(BookDto bookDto, int creatorId);

    List<BookLiteDto> getAllBooks();

    List<BookLiteDto> searchByTitle(String title);

    BookDetailsDto findById(Integer id);

    boolean deleteBook(int userId, int bookId);

    boolean updateBook(BookDto bookDto, int userId);
}
