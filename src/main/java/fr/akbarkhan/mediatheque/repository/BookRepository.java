package fr.akbarkhan.mediatheque.repository;

import fr.akbarkhan.mediatheque.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(concat('%', :title,'%'))")
    List<Book> searchAllByTitle(@Param("title") String title);
}
