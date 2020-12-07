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
    public BookDetailsDto findById(Integer id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            CreatorDto creatorDto = new CreatorDto(
                    book.getCreator().getId(),
                    book.getCreator().getFirstName(),
                    book.getCreator().getLastName()
            );
            return new BookDetailsDto(
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getGenre(),
                    book.getSummary(),
                    book.getYear(),
                    creatorDto
            );
        } else {
            return null;
        }

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
    public List<BookDetailsDto> getAllBooks() {
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
    public boolean saveBook(BookDto bookDto, int creatorId) {
        // todo: improve with findByIsbn => if is null then save
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());
        book.setYear(bookDto.getYear());
        book.setSummary(bookDto.getSummary());
        Optional<MyUser> creator = userRepository.findById(creatorId);
        book.setCreator(creator.orElse(null));
        bookRepository.save(book);
        return true;
    }

    @Override
    public boolean updateBook(BookDto bookDto, int userId) {
        Book bookStored = bookRepository.findById(bookDto.getId()).orElse(null);

        if (bookStored != null && bookStored.getCreator().getId().equals(userId)) {
            bookStored.setTitle(bookDto.getTitle());
            bookStored.setAuthor(bookDto.getAuthor());
            bookStored.setGenre(bookDto.getGenre());
            bookStored.setYear(bookDto.getYear());
            bookStored.setSummary(bookDto.getSummary());
            bookRepository.save(bookStored);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteBook(int userId, int bookId) {
        long count1 = bookRepository.count();
        Book bookToRemove = bookRepository.findById(bookId).orElse(null);
        if (bookToRemove != null && bookToRemove.getCreator().getId() == userId) {
            List<MyUser> bookUsers = userRepository.findAll()
                    .stream().filter(user -> user.getBookList().contains(bookToRemove)).collect(Collectors.toList());

            bookUsers.forEach(user_ -> user_.getBookList().remove(bookToRemove));
            userRepository.saveAll(bookUsers);
            bookRepository.delete(bookToRemove);
            return bookRepository.count() < count1;
        }
        return false;

    }
}
