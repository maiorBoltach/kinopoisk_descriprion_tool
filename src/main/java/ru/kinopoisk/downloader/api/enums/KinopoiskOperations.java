package ru.kinopoisk.downloader.api.enums;

public enum KinopoiskOperations {
    FILM_DETAILS("getKPFilmDetailView");

    private final String val;

    KinopoiskOperations(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }
}
