package fr.akbarkhan.mediatheque.repository;

import fr.akbarkhan.mediatheque.entity.MyUser;
import fr.akbarkhan.mediatheque.entity.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserBookRepository extends JpaRepository<UserBook, Integer> {

    @Query("SELECT ub from UserBook ub WHERE ub.reader.id = :reader")
    List<UserBook> findAllByReaderId(@Param("reader") int reader);

}
