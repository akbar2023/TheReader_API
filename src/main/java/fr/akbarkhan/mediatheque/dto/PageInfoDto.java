package fr.akbarkhan.mediatheque.dto;

import java.util.List;

public class PageInfoDto {
    private List<BookLiteDto> bookLiteDto;
    private long totalPages;
    private long totalElements;

    public PageInfoDto(List<BookLiteDto> bookLiteDto, long totalPages, long totalElements) {
        this.bookLiteDto = bookLiteDto;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public List<BookLiteDto> getBookLiteDto() {
        return bookLiteDto;
    }

    public void setBookLiteDto(List<BookLiteDto> bookLiteDto) {
        this.bookLiteDto = bookLiteDto;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    @Override
    public String toString() {
        return "PageInfoDto{" +
                "bookLiteDto=" + bookLiteDto +
                ", totalPage=" + totalPages +
                ", totalElements=" + totalElements +
                '}';
    }
}


