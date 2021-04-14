package fr.akbarkhan.mediatheque.service;

import fr.akbarkhan.mediatheque.dto.BookDetailsDto;
import fr.akbarkhan.mediatheque.dto.BookDto;
import fr.akbarkhan.mediatheque.dto.PageableBooksDto;

public interface BookService {

    PageableBooksDto getAllBooksPageable(int page, int size);

    boolean saveBook(BookDto bookDto, int creatorId);

    PageableBooksDto searchByTitle(String title, int page, int size);

    BookDetailsDto findById(Integer id);

    boolean deleteBook(int userId, int bookId);

    boolean updateBook(BookDto bookDto, int userId);
}
