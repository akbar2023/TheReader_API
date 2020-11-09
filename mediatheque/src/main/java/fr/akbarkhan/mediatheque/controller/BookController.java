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
import java.util.Optional;

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

    //todo: manage optional on services
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN, USER')")
    public Optional<Book> getById(@PathVariable("id") int id) {
        return bookService.findById(id);
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

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN, USER')")
    public String updateBook(@Valid @RequestBody BookDto bookDto, @PathVariable("id") Integer id) {
        bookService.updateBook(bookDto, id);
        return "Update success!";
    }

}
