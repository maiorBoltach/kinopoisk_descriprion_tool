package ru.kinopoisk.api.models;

import java.util.ArrayList;
import java.util.Objects;

public class Film {
    private ArrayList<Creators> filmCreators;
    private Integer filmID;
    private String filmURL;
    private String filmNameEn;
    private String filmNameRu;
    private String filmLentgh;
    private String filmDescription;
    private String filmSlogan;
    private String filmPremiereDVD;
    private String filmPremiereBluRay;
    private boolean hasAwards;
    private String filmType;
    private String filmGenres;
    private String filmCountries;
    private String year;
    private Double kinopoiskRating;
    private Double imdbRating;
    private Integer worldwideCriticsPercentRating;
    private Long budget;
    private Long boxOffice;
    private boolean is3DAvailable;

    public Film() {
        filmID = null;
        filmURL = null;
        filmNameEn = null;
        filmNameRu = null;
        filmLentgh = null;
        filmDescription = null;
        filmSlogan = null;
        filmPremiereDVD = null;
        filmPremiereBluRay = null;
        hasAwards = false;
        filmType = null;
        filmGenres = null;
        filmCountries = null;
        year = null;
        kinopoiskRating = null;
        imdbRating = null;
        worldwideCriticsPercentRating = null;
        budget = null;
        boxOffice = null;
        is3DAvailable = false;
        filmCreators = null;
    }

    public Integer getFilmID() {
        return filmID;
    }

    public void setFilmID(Integer filmID) {
        this.filmID = filmID;
    }

    public String getFilmURL() {
        return filmURL;
    }

    public void setFilmURL(String filmURL) {
        this.filmURL = filmURL;
    }

    public String getFilmNameEn() {
        return filmNameEn;
    }

    public void setFilmNameEn(String filmNameEn) {
        this.filmNameEn = filmNameEn;
    }

    public String getFilmNameRu() {
        return filmNameRu;
    }

    public void setFilmNameRu(String filmNameRu) {
        this.filmNameRu = filmNameRu;
    }

    public String getFilmLentgh() {
        return filmLentgh;
    }

    public void setFilmLentgh(String filmLentgh) {
        this.filmLentgh = filmLentgh;
    }

    public String getFilmDescription() {
        return filmDescription;
    }

    public void setFilmDescription(String filmDescription) {
        this.filmDescription = filmDescription;
    }

    public String getFilmSlogan() {
        return filmSlogan;
    }

    public ArrayList<Creators> getFilmCreators() {
        return filmCreators;
    }

    public void setFilmCreators(ArrayList<Creators> filmCreators) {
        this.filmCreators = filmCreators;
    }

    public void setFilmSlogan(String filmSlogan) {
        this.filmSlogan = filmSlogan;
    }

    public String getFilmPremiereDVD() {
        return filmPremiereDVD;
    }

    public void setFilmPremiereDVD(String filmPremiereDVD) {
        this.filmPremiereDVD = filmPremiereDVD;
    }

    public String getFilmPremiereBluRay() {
        return filmPremiereBluRay;
    }

    public void setFilmPremiereBluRay(String filmPremiereBluRay) {
        this.filmPremiereBluRay = filmPremiereBluRay;
    }

    public boolean isHasAwards() {
        return hasAwards;
    }

    public void setHasAwards(boolean hasAwards) {
        this.hasAwards = hasAwards;
    }

    public String getFilmType() {
        return filmType;
    }

    public void setFilmType(String filmType) {
        switch (filmType) {
            case "KPFilm":
                this.filmType = "MOVIE";
            case "KPSerial":
                this.filmType = "SERIAL";
            default:
                this.filmType = "MOVIE";
        }
    }

    public String getFilmGenres() {
        return filmGenres;
    }

    public void setFilmGenres(String filmGenres) {
        this.filmGenres = filmGenres;
    }

    public String getFilmCountries() {
        return filmCountries;
    }

    public void setFilmCountries(String filmCountries) {
        this.filmCountries = filmCountries;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Double getKinopoiskRating() {
        return kinopoiskRating;
    }

    public void setKinopoiskRating(Double kinopoiskRating) {
        this.kinopoiskRating = kinopoiskRating;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public Integer getWorldwideCriticsPercentRating() {
        return worldwideCriticsPercentRating;
    }

    public void setWorldwideCriticsPercentRating(Integer worldwideCriticsPercentRating) {
        this.worldwideCriticsPercentRating = worldwideCriticsPercentRating;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public Long getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(Long boxOffice) {
        this.boxOffice = boxOffice;
    }

    public Boolean is3DAvailable() {
        return is3DAvailable;
    }

    public void setIs3DAvailable(boolean is3DAvailable) {
        this.is3DAvailable = is3DAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return isHasAwards() == film.isHasAwards() &&
                is3DAvailable() == film.is3DAvailable() &&
                Objects.equals(getFilmID(), film.getFilmID()) &&
                Objects.equals(getFilmURL(), film.getFilmURL()) &&
                Objects.equals(getFilmNameEn(), film.getFilmNameEn()) &&
                Objects.equals(getFilmNameRu(), film.getFilmNameRu()) &&
                Objects.equals(getFilmLentgh(), film.getFilmLentgh()) &&
                Objects.equals(getFilmDescription(), film.getFilmDescription()) &&
                Objects.equals(getFilmSlogan(), film.getFilmSlogan()) &&
                Objects.equals(getFilmPremiereDVD(), film.getFilmPremiereDVD()) &&
                Objects.equals(getFilmPremiereBluRay(), film.getFilmPremiereBluRay()) &&
                Objects.equals(getFilmType(), film.getFilmType()) &&
                Objects.equals(getFilmGenres(), film.getFilmGenres()) &&
                Objects.equals(getFilmCountries(), film.getFilmCountries()) &&
                Objects.equals(getYear(), film.getYear()) &&
                Objects.equals(getKinopoiskRating(), film.getKinopoiskRating()) &&
                Objects.equals(getImdbRating(), film.getImdbRating()) &&
                Objects.equals(getWorldwideCriticsPercentRating(), film.getWorldwideCriticsPercentRating()) &&
                Objects.equals(getBudget(), film.getBudget()) &&
                Objects.equals(getBoxOffice(), film.getBoxOffice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFilmID(), getFilmURL(), getFilmNameEn(), getFilmNameRu(), getFilmLentgh(), getFilmDescription(),
                getFilmSlogan(), getFilmPremiereDVD(), getFilmPremiereBluRay(), isHasAwards(), getFilmType(), getFilmGenres(),
                getFilmCountries(), getYear(), getKinopoiskRating(), getImdbRating(), getWorldwideCriticsPercentRating(),
                getBudget(), getBoxOffice(), is3DAvailable());
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmID=" + filmID +
                ", filmURL='" + filmURL + '\'' +
                ", filmNameEn='" + filmNameEn + '\'' +
                ", filmNameRu='" + filmNameRu + '\'' +
                ", filmLentgh='" + filmLentgh + '\'' +
                ", filmDescription='" + filmDescription + '\'' +
                ", filmSlogan='" + filmSlogan + '\'' +
                ", filmPremiereDVD='" + filmPremiereDVD + '\'' +
                ", filmPremiereBluRay='" + filmPremiereBluRay + '\'' +
                ", hasAwards=" + hasAwards +
                ", filmType=" + filmType +
                ", filmGenres=" + filmGenres +
                ", filmCountries=" + filmCountries +
                ", year=" + year +
                ", kinopoiskRating=" + kinopoiskRating +
                ", imdbRating=" + imdbRating +
                ", worldwideCriticsPercentRating=" + worldwideCriticsPercentRating +
                ", budget=" + budget +
                ", boxOffice=" + boxOffice +
                ", is3DAvailable=" + is3DAvailable +
                '}';
    }
}
