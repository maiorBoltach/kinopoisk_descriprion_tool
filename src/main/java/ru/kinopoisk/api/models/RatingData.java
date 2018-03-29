package ru.kinopoisk.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RatingData {
    private Double rating;
    private Double ratingIMDb;
    private Integer ratingFilmCritics;

    public RatingData() {
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setRating(String rating) {
        this.rating = Double.parseDouble(rating.replaceAll(" ", ""));
    }

    public Double getRatingIMDb() {
        return ratingIMDb;
    }

    public void setRatingIMDb(Double ratingIMDb) {
        this.ratingIMDb = ratingIMDb;
    }

    public void setRatingIMDb(String ratingIMDb) {
        this.ratingIMDb = Double.parseDouble(ratingIMDb);
    }

    public Integer getRatingFilmCritics() {
        return ratingFilmCritics;
    }

    public void setRatingFilmCritics(Integer ratingFilmCritics) {
        this.ratingFilmCritics = ratingFilmCritics;
    }

    public void setRatingFilmCritics(String ratingFilmCritics) {
        this.ratingFilmCritics = Integer.parseInt(ratingFilmCritics.replaceAll("%", ""));
    }

    @Override
    public String toString() {
        return "RatingData{" +
                "rating=" + rating +
                ", ratingIMDb=" + ratingIMDb +
                ", ratingFilmCritics=" + ratingFilmCritics +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingData that = (RatingData) o;
        return Objects.equals(getRating(), that.getRating()) &&
                Objects.equals(getRatingIMDb(), that.getRatingIMDb()) &&
                Objects.equals(getRatingFilmCritics(), that.getRatingFilmCritics());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getRating(), getRatingIMDb(), getRatingFilmCritics());
    }
}
