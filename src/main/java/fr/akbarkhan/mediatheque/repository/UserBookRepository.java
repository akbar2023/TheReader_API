package fr.akbarkhan.mediatheque.repository;

import fr.akbarkhan.mediatheque.dto.BookLiteDto;
import fr.akbarkhan.mediatheque.entity.UserBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserBookRepository extends JpaRepository<UserBook, Integer> {

    // todo: return  list of userBookDto
    @Query("SELECT ub FROM UserBook ub WHERE ub.reader.id = :reader ORDER BY ub.id DESC")
    List<UserBook> findAllByReaderId(@Param("reader") int reader);

    // todo: return  list of userBookDto
    @Query("SELECT ub FROM UserBook ub WHERE ub.book.id = :bookId")
    List<UserBook> findAllByBookId(@Param("bookId") int bookId);

    @Query("SELECT ub.book.id FROM UserBook ub WHERE ub.reader.id = :readerId")
    List<Integer> findReadingBooksIdByReaderId(@Param("readerId") int readerId);

    @Query("SELECT ub FROM UserBook ub WHERE ub.reader.id = :readerId AND ub.book.id = :bookId")
    List<UserBook> findReadingsWithBookIdReaderId(Integer readerId, int bookId);

    @Query("SELECT new fr.akbarkhan.mediatheque.dto.BookLiteDto(ub.id, ub.book.title, ub.book.author) FROM UserBook ub")
    Page<BookLiteDto> booksByPage(Pageable pageable);
}
