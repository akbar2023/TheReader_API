package fr.akbarkhan.mediatheque.dto;

import javax.validation.constraints.NotNull;

public class ReadingDto {

    @NotNull
    int id;

    @NotNull
    boolean isRead;

    public ReadingDto(int id, boolean isRead) {
        this.id = id;
        this.isRead = isRead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
