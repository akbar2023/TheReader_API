package fr.akbarkhan.mediatheque.controller;

import fr.akbarkhan.mediatheque.dto.BookDto;
import fr.akbarkhan.mediatheque.entity.Book;
import fr.akbarkhan.mediatheque.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN, USER')")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN, USER')")
    public Optional<Book> getById(@PathVariable("id") int id) {
        return bookService.findById(id);
    }

    @GetMapping("/title/{title}")
    @PreAuthorize("hasAnyRole('ADMIN, USER')")
    public List<Book> getByName(@PathVariable("title") String title) {
        List<Book> list = bookService.findByTitle(title);
        return list;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Book addBook(@RequestBody BookDto bookDto) {
        Book book = bookService.saveBook(bookDto);
        return book;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateBook(@RequestBody BookDto bookDto, @PathVariable("id") Integer id) {
        bookService.updateBook(bookDto, id);
        return "Update success!";
    }

}
