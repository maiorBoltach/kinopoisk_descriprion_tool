package ru.kinopoisk.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Film {
    private Integer filmID;
    private String webURL;
    private String nameEN;
    private String nameRU;
    private String filmLength;
    private String description;
    private String slogan;
    private String year;
    private String type;
    private String genre;
    private String country;
    private boolean hasAwards;
    private boolean is3D;
    private List<Creator> creators;
    private RatingData ratingData;
    private RentData rentData;
    private BudgetData budgetData;

    public Film() {
        this.creators = null;
        this.budgetData = null;
        this.rentData = null;
        this.ratingData = null;
        this.filmID = null;
        this.webURL = null;
        this.nameEN = null;
        this.nameRU = null;
        this.filmLength = null;
        this.description = null;
        this.slogan = null;
        this.year = null;
        this.type = null;
        this.genre = null;
        this.country = null;
        this.hasAwards = false;
        this.is3D = false;
    }

    public List<Creator> getCreators() {
        return creators;
    }

    public void setCreators(Creator[][] creators) {
        this.creators = Arrays.stream(creators)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }

    public RatingData getRatingData() {
        return ratingData;
    }

    public void setRatingData(RatingData ratingData) {
        this.ratingData = ratingData;
    }

    public RentData getRentData() {
        return rentData;
    }

    public void setRentData(RentData rentData) {
        this.rentData = rentData;
    }

    public BudgetData getBudgetData() {
        return budgetData;
    }

    public void setBudgetData(BudgetData budgetData) {
        this.budgetData = budgetData;
    }

    public Integer getFilmID() {
        return filmID;
    }

    public void setFilmID(Integer filmID) {
        this.filmID = filmID;
    }

    public String getWebURL() {
        return webURL;
    }

    public void setWebURL(String webURL) {
        this.webURL = webURL;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getNameRU() {
        return nameRU;
    }

    public void setNameRU(String nameRU) {
        this.nameRU = nameRU;
    }

    public String getFilmLength() {
        return filmLength;
    }

    public void setFilmLength(String filmLength) {
        this.filmLength = filmLength;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.replaceAll("\n\r", " ");
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        switch (type) {
            case "KPFilm":
                this.type = "MOVIE";
            case "KPSerial":
                this.type = "SERIAL";
            default:
                this.type = "MOVIE";
        }
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isHasAwards() {
        return hasAwards;
    }

    public void setHasAwards(boolean hasAwards) {
        this.hasAwards = hasAwards;
    }

    public boolean isIs3D() {
        return is3D;
    }

    public void setIs3D(boolean is3D) {
        this.is3D = is3D;
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmID=" + filmID +
                ", webURL='" + webURL + '\'' +
                ", nameEN='" + nameEN + '\'' +
                ", nameRU='" + nameRU + '\'' +
                ", filmLength='" + filmLength + '\'' +
                ", description='" + description + '\'' +
                ", slogan='" + slogan + '\'' +
                ", year='" + year + '\'' +
                ", type='" + type + '\'' +
                ", genre='" + genre + '\'' +
                ", country='" + country + '\'' +
                ", hasAwards=" + hasAwards +
                ", is3D=" + is3D +
                ", filmCreators=" + creators +
                ", ratingData=" + ratingData +
                ", rentData=" + rentData +
                ", budgetData=" + budgetData +
                '}';
    }
}
