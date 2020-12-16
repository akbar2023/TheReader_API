package fr.akbarkhan.mediatheque.dto;

public class ReadingBookDto {

    private int readingId;

    private int bookId;

    private String author;

    private String title;

    private boolean read;

    public ReadingBookDto() {
    }

    public ReadingBookDto(int readingId, int bookId, String author, String title, boolean read) {
        this.readingId = readingId;
        this.bookId = bookId;
        this.author = author;
        this.title = title;
        this.read = read;
    }

    public int getReadingId() {
        return readingId;
    }

    public void setReadingId(int readingId) {
        this.readingId = readingId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return "ReadingBookDto{" +
                "readingId=" + readingId +
                ", bookId=" + bookId +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", read=" + read +
                '}';
    }
}
