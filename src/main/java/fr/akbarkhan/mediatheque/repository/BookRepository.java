package fr.akbarkhan.mediatheque.repository;

import fr.akbarkhan.mediatheque.dto.BookLiteDto;
import fr.akbarkhan.mediatheque.dto.PageableBooksDto;
import fr.akbarkhan.mediatheque.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(concat('%', :title,'%'))")
    List<Book> searchAllByTitle(@Param("title") String title);


    @Query("SELECT new fr.akbarkhan.mediatheque.dto.BookLiteDto(b.id, b.title, b.author) FROM Book b")
    Page<BookLiteDto> findAllBooksLite(Pageable pageable);
}
