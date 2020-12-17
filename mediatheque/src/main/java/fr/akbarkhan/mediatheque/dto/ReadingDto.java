package fr.akbarkhan.mediatheque.dto;

public class ReadingDto {

    private int readingId;

    private int bookId;

    private String author;

    private String title;

    private boolean isRead;

    public ReadingDto() {
    }

    public ReadingDto(int readingId, int bookId, String author, String title, boolean isRead) {
        this.readingId = readingId;
        this.bookId = bookId;
        this.author = author;
        this.title = title;
        this.isRead = isRead;
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
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    @Override
    public String toString() {
        return "ReadingDto{" +
                "readingId=" + readingId +
                ", bookId=" + bookId +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", isRead=" + isRead +
                '}';
    }
}
