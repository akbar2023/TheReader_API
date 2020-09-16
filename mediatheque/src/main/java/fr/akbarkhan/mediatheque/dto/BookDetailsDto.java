package fr.akbarkhan.mediatheque.dto;

public class BookDetailsDto {

    private Integer id;
    private String title;
    private String author;
    private String genre;
    private String summary;
    private Integer year;
    private CreatorDto creator;

    public BookDetailsDto(Integer id,
                          String title,
                          String author,
                          String genre,
                          String summary,
                          Integer year,
                          CreatorDto creator) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.summary = summary;
        this.year = year;
        this.creator = creator;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public CreatorDto getCreator() {
        return creator;
    }

    public void setCreator(CreatorDto creator) {
        this.creator = creator;
    }
}
