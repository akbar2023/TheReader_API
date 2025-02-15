package fr.akbarkhan.mediatheque.entity;

import javax.persistence.*;

@Entity
@Table
public class UserBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id")
    private Book book;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    private MyUser reader;

    @Column(nullable = false)
    private boolean isRead;

    @Column(nullable = false)
    private boolean isFavorite;

    public UserBook() {
    }

    public UserBook(Book book, MyUser reader, boolean isRead, boolean isFavorite) {
        this.book = book;
        this.reader = reader;
        this.isRead = isRead;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public MyUser getReader() {
        return reader;
    }

    public void setReader(MyUser reader) {
        this.reader = reader;
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
        return "UserBook{" +
                "id=" + id +
                ", book=" + book +
                ", reader=" + reader +
                ", isRead=" + isRead +
                ", isFavorite=" + isFavorite +
                '}';
    }
}
