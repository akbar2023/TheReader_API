package fr.akbarkhan.mediatheque.controller;

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
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN, USER')")
    public List<BookDetailsDto> getAllBooks() {
        return bookService.findAllWithCreator();
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
    public List<Book> getByName(@PathVariable("title") String title) {
        return bookService.findByTitle(title);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN, USER')")
    public ResponseEntity<?> addBook(@Valid @RequestBody BookDto bookDto) {
        return bookService.saveBook(bookDto) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN, USER')")
    public ResponseEntity<?> updateBook(@Valid @RequestBody BookDto bookDto) {
        return bookService.updateBook(bookDto) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
