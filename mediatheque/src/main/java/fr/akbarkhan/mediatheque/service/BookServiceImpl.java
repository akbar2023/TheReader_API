package fr.akbarkhan.mediatheque.service;

import fr.akbarkhan.mediatheque.dto.BookDto;
import fr.akbarkhan.mediatheque.entity.Book;
import fr.akbarkhan.mediatheque.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Optional<Book> findById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findByTitle(String title) {
        Iterable<Book> books = bookRepository.findAllByTitle(title);
        List<Book> list = StreamSupport
                .stream(books.spliterator(), false)
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book saveBook(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());
        book.setYear(bookDto.getYear());
        return bookRepository.save(book);
    }

    @Override
    public void updateBook(BookDto bookDto, Integer id) {
        Book bookStored = bookRepository.findById(id).orElseThrow();
        bookStored.setTitle(bookDto.getTitle());
        bookStored.setAuthor(bookDto.getAuthor());
        bookStored.setGenre(bookDto.getGenre());
        bookStored.setYear(bookDto.getYear());
        bookRepository.save(bookStored);
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}
