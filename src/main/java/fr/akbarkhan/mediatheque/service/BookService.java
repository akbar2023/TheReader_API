package fr.akbarkhan.mediatheque.service;

import fr.akbarkhan.mediatheque.dto.BookDetailsDto;
import fr.akbarkhan.mediatheque.dto.BookDto;
import fr.akbarkhan.mediatheque.dto.BookLiteDto;
import fr.akbarkhan.mediatheque.dto.PageableBooksDto;

import java.util.List;

public interface BookService {

    PageableBooksDto getAllBooksPageable(int page, int size);

    boolean saveBook(BookDto bookDto, int creatorId);

    List<BookLiteDto> searchByTitle(String title);

    BookDetailsDto findById(Integer id);

    boolean deleteBook(int userId, int bookId);

    boolean updateBook(BookDto bookDto, int userId);
}
