package fr.akbarkhan.mediatheque.dto;

public class ReadingBookLiteDto {
    private int bookId;

    public ReadingBookLiteDto(int bookId) {
        this.bookId = bookId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "ReadingBookLiteDto{" +
                "bookId=" + bookId +
                '}';
    }
}
