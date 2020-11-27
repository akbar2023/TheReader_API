package fr.akbarkhan.mediatheque.dto;

public class UserBookDtoSecured {
    private int bookId;
    private String tokenBody;

    public UserBookDtoSecured(int bookId, String tokenBody) {
        this.bookId = bookId;
        this.tokenBody = tokenBody;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTokenBody() {
        return tokenBody;
    }

    public void setTokenBody(String tokenBody) {
        this.tokenBody = tokenBody;
    }
}
