package fr.akbarkhan.mediatheque.repository;

import fr.akbarkhan.mediatheque.dto.ReadingBookLiteDto;
import fr.akbarkhan.mediatheque.entity.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserBookRepository extends JpaRepository<UserBook, Integer> {

    @Query("SELECT ub FROM UserBook ub WHERE ub.reader.id = :reader")
    List<UserBook> findAllByReaderId(@Param("reader") int reader);

    @Query("SELECT ub FROM UserBook ub WHERE ub.book.id = :bookId")
    List<UserBook> findAllByBookId(@Param("bookId") int bookId);

    @Query("SELECT ub.book.id FROM UserBook ub WHERE ub.reader.id = :readerId")
    List<Integer> findReadingBooksIdByReaderId(@Param("readerId") int readerId);
}
