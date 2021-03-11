package fr.akbarkhan.mediatheque.dto;

public class ReadingDto {

    private int readingId;

    private int bookId;

    private int creatorId;

    private String author;

    private String title;

    private boolean isRead;

    private boolean isFavorite;

    public ReadingDto() {
    }

    public ReadingDto(int readingId, int bookId, int creatorId, String author, String title, boolean isRead, boolean isFavorite) {
        this.readingId = readingId;
        this.bookId = bookId;
        this.creatorId = creatorId;
        this.author = author;
        this.title = title;
        this.isRead = isRead;
        this.isFavorite = isFavorite;
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

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
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

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public String toString() {
        return "ReadingDto{" +
                "readingId=" + readingId +
                ", bookId=" + bookId +
                ", creatorId=" + creatorId +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", isRead=" + isRead +
                ", isFavorite=" + isFavorite +
                '}';
    }
}
