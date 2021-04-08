package fr.akbarkhan.mediatheque.service;

import fr.akbarkhan.mediatheque.dto.*;

import java.util.List;

public interface UserBookService {

    boolean addReadingBook(int bookId, int userId);

    List<ReadingDto> getReadingBooks(int userId);

    boolean updateReadingStatus(Integer userId, ReadingStatusDto readingStatusDto);

    boolean setFavoriteReading(Integer userId, FavoriteReadingDto favoriteReadingDto);

    boolean deleteReading(Integer userId, int readingId);

    List<Integer> getReadingIdBookId(Integer userIdFromToken);

    PageInfoDto getAllBooksByPage(int page, int size);

}
