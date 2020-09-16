package fr.akbarkhan.mediatheque.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BookDto {

    private int id;

    @NotBlank
    @Size(min = 2, max = 100)
    private String title;

    @NotBlank
    @Size(min = 2, max = 20)
    private String genre;

    @NotBlank
    @Size(min = 2, max = 100)
    private String author;

    @NotNull
    private Integer year;

    @NotBlank
    @Size(min = 10, max = 255)
    private String summary;

    @NotNull
    private Integer creatorId;

    public BookDto() {
    }

    public BookDto(
            String title,
            String genre,
            String author,
            Integer year,
            String summary,
            Integer creatorId) {
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.year = year;
        this.summary = summary;
        this.creatorId = creatorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", summary='" + summary + '\'' +
                ", creatorId=" + creatorId +
                '}';
    }
}
