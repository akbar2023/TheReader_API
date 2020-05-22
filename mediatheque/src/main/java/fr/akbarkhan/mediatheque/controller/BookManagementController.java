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
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());
        book.setYear(bookDto.getYear());
        bookService.saveBook(book);
        return book;
    }

    @DeleteMapping("/{bookId}")
    @PreAuthorize("hasAuthority('book:write')")
    public String deleteBook(@PathVariable("bookId") Integer id) {
        bookService.deleteBook(id);
        return "delete success!";
    }


}
