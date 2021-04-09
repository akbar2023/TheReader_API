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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserBookServiceImpl implements UserBookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserBookRepository userBookRepository;

    @Override
    public boolean addReadingBook(int bookId, int userId) {
        MyUser reader = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null && reader != null) {
            UserBook userBook = new UserBook(book, reader, false, false);
            userBookRepository.save(userBook);
            return true;
        }
        return false;
    }

    @Override
    public List<ReadingDto> getReadingBooks(int userId) {
        List<UserBook> all = userBookRepository.findAllByReaderId(userId);
        return all.stream().map(userBook ->
                new ReadingDto(userBook.getId(),
                        userBook.getBook().getId(),
                        userBook.getBook().getCreator().getId(),
                        userBook.getBook().getAuthor(),
                        userBook.getBook().getTitle(),
                        userBook.isRead(),
                        userBook.isFavorite()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateReadingStatus(Integer userId, ReadingStatusDto readingStatusDto) {
        UserBook userBook = userBookRepository.findById(readingStatusDto.getId()).orElse(null);
        if (userBook != null && userBook.getReader().getId().equals(userId)) {
            userBook.setRead(readingStatusDto.isRead());
            userBookRepository.save(userBook);
            return true;
        }
        return false;
    }

    @Override
    public boolean setFavoriteReading(Integer userId, FavoriteReadingDto favoriteReadingDto) {
        UserBook userBook = userBookRepository.findById(favoriteReadingDto.getId()).orElse(null);
        if (userBook != null && userBook.getReader().getId().equals(userId)) {
            userBook.setFavorite(favoriteReadingDto.isFavorite());
            userBookRepository.save(userBook);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReading(Integer readerId, int bookId) {
        List<UserBook> readings = userBookRepository.findReadingsWithBookIdReaderId(readerId, bookId);
        if (readings != null) {
            userBookRepository.deleteAll(readings);
            return true;
        }
        return false;
    }

    @Override
    public List<Integer> getReadingIdBookId(Integer readerId) {
        return userBookRepository.findReadingBooksIdByReaderId(readerId);
    }

    /*
    * Parameter PAGE defines the requested page,which starts from 0.
    * Parameter SIZE defines the number of elements in a requested page
    *
    * Example :If we want to display the first page with 4 elements within it, then the parameters should contain 0 and 4 respectively
    */
    @Override
    public PageableBooksDto getAllBooksByPage(int page, int size) {
        Pageable pageAndSize = PageRequest.of(page, size);
        Page<BookLiteDto> all = userBookRepository.booksByPage(pageAndSize);
        long totalElements = all.getTotalElements();
        int totalPages = all.getTotalPages();
        all.getPageable().getPageNumber();
        List<BookLiteDto> content = all.getContent();
        return new PageableBooksDto(content, totalPages, totalElements);
    }
}
