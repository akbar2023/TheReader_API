package fr.akbarkhan.mediatheque.dto;

import javax.validation.constraints.NotNull;

public class FavoriteReadingDto {
    @NotNull
    int id;

    @NotNull
    boolean isFavorite;

    public FavoriteReadingDto(@NotNull int id, @NotNull boolean isFavorite) {
        this.id = id;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
