package fr.akbarkhan.mediatheque.repository;

import fr.akbarkhan.mediatheque.dto.BookLiteDto;
import fr.akbarkhan.mediatheque.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT new fr.akbarkhan.mediatheque.dto.BookLiteDto(b.id, b.title, b.author) FROM Book b WHERE LOWER(b.title) LIKE LOWER(concat('%', :title,'%'))")
    Page<BookLiteDto> searchAllByTitle(@Param("title") String title, Pageable pageable);


    @Query("SELECT new fr.akbarkhan.mediatheque.dto.BookLiteDto(b.id, b.title, b.author) FROM Book b")
    Page<BookLiteDto> findAllBooksLite(Pageable pageable);
}
