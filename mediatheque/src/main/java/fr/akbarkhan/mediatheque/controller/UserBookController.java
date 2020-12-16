package fr.akbarkhan.mediatheque.controller;

import fr.akbarkhan.mediatheque.controller.Reusable.Methods;
import fr.akbarkhan.mediatheque.dto.ReadingBookDto;
import fr.akbarkhan.mediatheque.dto.ReadingDto;
import fr.akbarkhan.mediatheque.service.UserBookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ReadingBookDto> getReadings(Principal principal) {
        int userId = methods.getUserIdFromToken(principal);
        return userBookService.getReadingBooks(userId);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN, USER')")
    public ResponseEntity<?> updateReadingStatus(Principal principal, @Valid @RequestBody ReadingDto readingDto) {
        Integer userId = methods.getUserIdFromToken(principal);
        return methods.getResponseEntity(userBookService.updateReading(userId, readingDto));
    }

    @DeleteMapping("{readingId}")
    @PreAuthorize("hasAnyAuthority('ADMIN, USER')")
    public ResponseEntity<?> deleteReading(Principal principal, @PathVariable("readingId") int readingId) {
        Integer userId = methods.getUserIdFromToken(principal);
        return methods.getResponseEntity(userBookService.deleteReading(userId, readingId));
    }


}
