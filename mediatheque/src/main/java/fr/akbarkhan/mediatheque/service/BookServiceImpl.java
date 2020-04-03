package fr.akbarkhan.mediatheque.service;

import fr.akbarkhan.mediatheque.entity.Book;
import fr.akbarkhan.mediatheque.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Optional<Book> findById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public Iterable<Book> findByTitle(String title) {
        return bookRepository.findAllByTitle(title);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
