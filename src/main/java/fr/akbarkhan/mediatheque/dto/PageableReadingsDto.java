package fr.akbarkhan.mediatheque.dto;

import java.util.List;

public class PageableReadingsDto {

    private List<ReadingDto> readingDto;
    private long totalPages;
    private long totalElements;

    public PageableReadingsDto(List<ReadingDto> readingDto, long totalPages, long totalElements) {
        this.readingDto = readingDto;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public List<ReadingDto> getReadingDto() {
        return readingDto;
    }

    public void setReadingDto(List<ReadingDto> readingDto) {
        this.readingDto = readingDto;
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
        return "PageableReadingsDto{" +
                "readingDto=" + readingDto +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                '}';
    }
}
