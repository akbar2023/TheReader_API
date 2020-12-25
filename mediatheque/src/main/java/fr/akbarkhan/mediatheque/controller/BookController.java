package fr.akbarkhan.mediatheque.controller;

import fr.akbarkhan.mediatheque.controller.Reusable.Methods;
import fr.akbarkhan.mediatheque.dto.BookDetailsDto;
import fr.akbarkhan.mediatheque.dto.BookDto;
import fr.akbarkhan.mediatheque.entity.Book;
import fr.akbarkhan.mediatheque.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private Methods methods;

    @Autowired
    private BookService bookService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN, USER')")
    public List<BookDetailsDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN, USER')")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        BookDetailsDto book = bookService.findById(id);
        if (book != null) {
            return ResponseEntity.status(HttpStatus.OK).body(book);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // todo: change return type to BookDetailsDto
    @GetMapping("/title/{title}")
    @PreAuthorize("hasAnyAuthority('ADMIN, USER')")
    public List<BookDetailsDto> getByTitle(@PathVariable("title") String title) {
        return bookService.findByTitle(title);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN, USER')")
    public ResponseEntity<?> addBook(@Valid @RequestBody BookDto bookDto, Principal principal) {
        Integer userId = methods.getUserIdFromToken(principal);
        return methods.getResponseEntity(bookService.saveBook(bookDto, userId));
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN, USER')")
    public ResponseEntity<?> updateBook(@Valid @RequestBody BookDto bookDto, Principal principal) {
        Integer userId = methods.getUserIdFromToken(principal);
        return methods.getResponseEntity(bookService.updateBook(bookDto, userId));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable("bookId") Integer bookId, Principal principal) {
        Integer userId = methods.getUserIdFromToken(principal);
        return methods.getResponseEntity(bookService.deleteBook(userId, bookId));
    }

}
