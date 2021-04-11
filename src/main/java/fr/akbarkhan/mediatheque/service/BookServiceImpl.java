package fr.akbarkhan.mediatheque.service;

import fr.akbarkhan.mediatheque.dto.*;
import fr.akbarkhan.mediatheque.entity.Book;
import fr.akbarkhan.mediatheque.entity.MyUser;
import fr.akbarkhan.mediatheque.entity.UserBook;
import fr.akbarkhan.mediatheque.repository.BookRepository;
import fr.akbarkhan.mediatheque.repository.UserBookRepository;
import fr.akbarkhan.mediatheque.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserBookRepository userBookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public BookDetailsDto findById(Integer id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            CreatorDto creatorDto = new CreatorDto(
                    book.getCreator().getId(),
                    book.getCreator().getFirstName(),
                    book.getCreator().getLastName()
            );
            return new BookDetailsDto(
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getGenre(),
                    book.getSummary(),
                    book.getYear(),
                    creatorDto
            );
        } else {
            return null;
        }

    }

    @Override
    public List<BookLiteDto> searchByTitle(String title) {
        List<Book> books = bookRepository.searchAllByTitle(title);
        List<BookLiteDto> list = new ArrayList<>();
        for (Book book : books) {
            list.add(new BookLiteDto(book.getId(), book.getTitle(), book.getAuthor()));
        }
        return list;
    }

    @Override
    public PageableBooksDto getAllBooksPageable(int pageIndex, int pageSize) {
        Pageable pageAndSize = PageRequest.of(pageIndex, pageSize);
        Page<BookLiteDto> page = bookRepository.findAllBooksLite(pageAndSize);
        long totalElements = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<BookLiteDto> books = page.getContent();
        return new PageableBooksDto(books, totalPages, totalElements);
    }

    @Override
    public boolean saveBook(BookDto bookDto, int creatorId) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());
        book.setYear(bookDto.getYear());
        book.setSummary(bookDto.getSummary());
        Optional<MyUser> creator = userRepository.findById(creatorId);
        book.setCreator(creator.orElse(null));
        bookRepository.save(book);
        return true;
    }

    @Override
    public boolean updateBook(BookDto bookDto, int userId) {
        Book bookStored = bookRepository.findById(bookDto.getId()).orElse(null);

        if (bookStored != null && bookStored.getCreator().getId().equals(userId)) {
            bookStored.setTitle(bookDto.getTitle());
            bookStored.setAuthor(bookDto.getAuthor());
            bookStored.setGenre(bookDto.getGenre());
            bookStored.setYear(bookDto.getYear());
            bookStored.setSummary(bookDto.getSummary());
            bookRepository.save(bookStored);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteBook(int userId, int bookId) {
        Book bookToDelete = bookRepository.findById(bookId).orElse(null);
        if (bookToDelete != null && bookToDelete.getCreator().getId().equals(userId)) {
            List<UserBook> allBookReadings = userBookRepository.findAllByBookId(bookId);
            userBookRepository.deleteAll(allBookReadings);
            bookRepository.deleteById(bookId);
            return true;
        }
        return false;
    }
}
