package fr.akbarkhan.mediatheque.service;

import fr.akbarkhan.mediatheque.dto.BookDetailsDto;
import fr.akbarkhan.mediatheque.dto.BookDto;
import fr.akbarkhan.mediatheque.dto.CreatorDto;
import fr.akbarkhan.mediatheque.entity.Book;
import fr.akbarkhan.mediatheque.entity.MyUser;
import fr.akbarkhan.mediatheque.repository.BookRepository;
import fr.akbarkhan.mediatheque.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

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
    public List<BookDetailsDto> findAllWithCreator() {
        List<Book> allBooks = bookRepository.findAll();

        List<BookDetailsDto> books = new ArrayList<>();

        allBooks.forEach(book -> {
            CreatorDto creator = new CreatorDto(
                    book.getCreator().getId(),
                    book.getCreator().getFirstName(),
                    book.getCreator().getLastName()
            );
            List<Integer> users = book.getUsers().stream().map(MyUser::getId).collect(Collectors.toList());
            BookDetailsDto bookDetailsDto = new BookDetailsDto(
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getGenre(),
                    book.getSummary(),
                    book.getYear(),
                    creator
            );
            bookDetailsDto.setUsers(users);
            books.add(bookDetailsDto);
        });
        return books;
    }

    @Override
    public boolean saveBook(BookDto bookDto) {
        // todo: improve with findByIsbn => if is null then save
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());
        book.setYear(bookDto.getYear());
        book.setSummary(bookDto.getSummary());
        Integer creatorId = bookDto.getCreatorId();
        Optional<MyUser> creator = userRepository.findById(creatorId);
        book.setCreator(creator.orElse(null));
        bookRepository.save(book);
        return true;
    }

    @Override
    public void updateBook(BookDto bookDto, Integer id) {
        Book bookStored = bookRepository.findById(id).orElseThrow();
        bookStored.setTitle(bookDto.getTitle());
        bookStored.setAuthor(bookDto.getAuthor());
        bookStored.setGenre(bookDto.getGenre());
        bookStored.setYear(bookDto.getYear());
        bookStored.setSummary(bookDto.getSummary());
        bookRepository.save(bookStored);
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}
