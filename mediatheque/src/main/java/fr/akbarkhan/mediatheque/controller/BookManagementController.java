package fr.akbarkhan.mediatheque.controller;

import fr.akbarkhan.mediatheque.dto.BookDto;
import fr.akbarkhan.mediatheque.entity.Book;
import fr.akbarkhan.mediatheque.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/management/book")
public class BookManagementController {

    @Autowired
    private BookService bookService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('book:write')")
    public Book createBook(@RequestBody BookDto bookDto) {
        return bookService.saveBook(bookDto);
    }

    @DeleteMapping("/{bookId}")
    @PreAuthorize("hasAuthority('book:write')")
    public String deleteBook(@PathVariable("bookId") Integer id) {
        bookService.deleteBook(id);
        return "delete success!";
    }


}
