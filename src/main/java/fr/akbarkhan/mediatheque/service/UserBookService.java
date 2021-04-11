package fr.akbarkhan.mediatheque.service;

import fr.akbarkhan.mediatheque.dto.FavoriteReadingDto;
import fr.akbarkhan.mediatheque.dto.ReadingDto;
import fr.akbarkhan.mediatheque.dto.ReadingStatusDto;

import java.util.List;

public interface UserBookService {

    boolean addReadingBook(int bookId, int userId);

    List<ReadingDto> getReadingBooks(int userId);

    boolean updateReadingStatus(Integer userId, ReadingStatusDto readingStatusDto);

    boolean setFavoriteReading(Integer userId, FavoriteReadingDto favoriteReadingDto);

    boolean deleteReading(Integer userId, int readingId);

    List<Integer> getReadingIdBookId(Integer userIdFromToken);

}
