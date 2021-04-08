package fr.akbarkhan.mediatheque.controller;

import fr.akbarkhan.mediatheque.controller.Reusable.Methods;
import fr.akbarkhan.mediatheque.dto.BookLiteDto;
import fr.akbarkhan.mediatheque.dto.FavoriteReadingDto;
import fr.akbarkhan.mediatheque.dto.PageInfoDto;
import fr.akbarkhan.mediatheque.dto.ReadingStatusDto;
import fr.akbarkhan.mediatheque.service.UserBookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/reading/")
public class UserBookController {

    @Autowired
    private Methods methods;

    @Autowired
    private UserBookServiceImpl userBookService;

    @PostMapping("{bookId}")
    @PreAuthorize("hasAnyAuthority('ADMIN, USER')")
    public ResponseEntity<?> addReading(@PathVariable("bookId") Integer bookId, Principal principal) {
        int userId = methods.getUserIdFromToken(principal);
        return methods.getResponseEntity(userBookService.addReadingBook(bookId, userId));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN, USER')")
    public ResponseEntity<?> getReadings(Principal principal) {
        int userId = methods.getUserIdFromToken(principal);
        return ResponseEntity.status(HttpStatus.OK).body(userBookService.getReadingBooks(userId));
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN, USER')")
    public ResponseEntity<?> updateReadingStatus(Principal principal, @Valid @RequestBody ReadingStatusDto readingStatusDto) {
        Integer userId = methods.getUserIdFromToken(principal);
        return methods.getResponseEntity(userBookService.updateReadingStatus(userId, readingStatusDto));
    }

    @PutMapping("/favorite")
    @PreAuthorize("hasAnyAuthority('ADMIN, USER')")
    public ResponseEntity<?> setFavoriteReading(Principal principal, @Valid @RequestBody FavoriteReadingDto favoriteReadingDto) {
        Integer userId = methods.getUserIdFromToken(principal);
        return methods.getResponseEntity(userBookService.setFavoriteReading(userId, favoriteReadingDto));
    }

    @DeleteMapping("{bookId}")
    @PreAuthorize("hasAnyAuthority('ADMIN, USER')")
    public ResponseEntity<?> deleteReading(Principal principal, @PathVariable("bookId") int bookId) {
        Integer userId = methods.getUserIdFromToken(principal);
        return methods.getResponseEntity(userBookService.deleteReading(userId, bookId));
    }

    @GetMapping("reading-book-ids")
    @PreAuthorize("hasAnyAuthority('ADMIN, USER')")
    public List<Integer> getReadingsIds(Principal principal) {
        Integer userId = methods.getUserIdFromToken(principal);
        return userBookService.getReadingIdBookId(userId);
    }

    @GetMapping("page/{page}/{size}")
    @PreAuthorize("hasAnyAuthority('ADMIN, USER')")
    public ResponseEntity<?> getPages(@PathVariable("page") int page, @PathVariable("size") int size) {
        PageInfoDto allBooksByPage = userBookService.getAllBooksByPage(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(allBooksByPage);
    }
}
