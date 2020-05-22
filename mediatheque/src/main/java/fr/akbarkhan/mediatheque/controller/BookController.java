package fr.akbarkhan.mediatheque.controller;

import fr.akbarkhan.mediatheque.dto.BookDto;
import fr.akbarkhan.mediatheque.entity.Book;
import fr.akbarkhan.mediatheque.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public String addBook(@RequestBody BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());
        book.setYear(bookDto.getYear());
        bookService.saveBook(book);
        return bookDto.getTitle() + " success!";
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Book> getById(@PathVariable int id) {
        return bookService.findById(id);
    }

    @GetMapping("/title/{title}")
    public List<Book> getByName(@PathVariable String title) {
        Iterable<Book> iterable = bookService.findByTitle(title);
        List<Book> list = StreamSupport
                .stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
        return list;
    }

    @PutMapping("/{id}")
    public String updateBook(@RequestBody BookDto bookDto, @PathVariable int id) {
        Book book = bookService.findById(id).orElseThrow();
//        bookDto.setTitle(book.getTitle());
//        bookDto.setGenre(book.getGenre());
        book.setTitle(bookDto.getTitle());
        book.setGenre(bookDto.getGenre());
        bookService.saveBook(book);
        return "Book of id " + id + "'s new title is: " + book.getTitle() + ", with update success!!";
    }

}
