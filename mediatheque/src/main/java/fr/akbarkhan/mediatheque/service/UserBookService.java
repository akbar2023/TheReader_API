package fr.akbarkhan.mediatheque.service;

import fr.akbarkhan.mediatheque.dto.ReadingBookDto;
import fr.akbarkhan.mediatheque.dto.ReadingDto;

import java.util.List;

public interface UserBookService {

    boolean addReadingBook(int bookId, int userId);

    List<ReadingBookDto> getReadingBooks(int userId);

    boolean updateReading(Integer userId, ReadingDto readingDto);
}
