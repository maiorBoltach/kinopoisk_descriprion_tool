package ru.kinopoisk.api.models;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;

public class Film {
    public enum MPAAType {G, PG, PG_13, NC_17, R, NAN}

    public enum AgeType {AGE_ANY, AGE_0_PLUS, AGE_6_PLUS, AGE_12_PLUS, AGE_16_PLUS, AGE_18_PLUS}

    public enum FilmType {ANY, MOVIE, SERIAL, CARTOON}

    public enum City {MINSK, MOSCOW, MOGILEV}

    public enum Genre {
        COMEDY("комедия"),
        ACTION("боевик"),
        DETECTIVE("детектив"),
        ANIME("аниме"),
        HORROR("ужасы");

        private String genre;

        Genre(String genre) {
            this.genre = genre;
        }

        public String getGenreAsString() {
            return genre;
        }
    }

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
    private FilmType filmType;
    private ArrayList<String> filmGenres;
    private ArrayList<String> filmCountries;
    private ArrayList<Creators> filmCreators;
    private String year;
    private Double kinopoiskRating;
    private Double imdbRating;
    private Integer worldwideCriticsPercentRating;
    private Integer numberPositiveReviews;
    private MPAAType mpaaRating;
    private AgeType ageRating;
    private Long budget;
    private Long boxOffice;
    private boolean isDVDAvailable;
    private boolean isBluRayAvailable;
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
        numberPositiveReviews = null;
        mpaaRating = null;
        ageRating = null;
        budget = null;
        boxOffice = null;
        isDVDAvailable = false;
        isBluRayAvailable = false;
        is3DAvailable = false;
        filmCreators = null;
    }

    public Film(String filmNameRu, FilmType filmType, ArrayList<String> filmGenres, ArrayList<String> filmCountries, String year,
                Double kinopoiskRating, Double imdbRating, Integer worldwideCriticsPercentRating, Integer numberPositiveReviews,
                MPAAType mpaaRating, AgeType ageRating, Long budget, Long boxOffice, boolean isDVDAvailable, boolean isBluRayAvailable,
                boolean is3DAvailable) {
        this();
        this.filmNameRu = filmNameRu;
        this.filmType = filmType;
        this.filmGenres = filmGenres;
        this.filmCountries = filmCountries;
        this.year = year;
        this.kinopoiskRating = kinopoiskRating;
        this.imdbRating = imdbRating;
        this.worldwideCriticsPercentRating = worldwideCriticsPercentRating;
        this.numberPositiveReviews = numberPositiveReviews;
        this.mpaaRating = mpaaRating;
        this.ageRating = ageRating;
        this.budget = budget;
        this.boxOffice = boxOffice;
        this.isDVDAvailable = isDVDAvailable;
        this.isBluRayAvailable = isBluRayAvailable;
        this.is3DAvailable = is3DAvailable;
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

    public FilmType getFilmType() {
        return filmType;
    }

    public void setFilmType(FilmType filmType) {
        this.filmType = filmType;
    }

    public ArrayList<String> getFilmGenres() {
        return filmGenres;
    }

    public String getFilmGenresInString() {
        String result = null;
        if(filmGenres != null) {
            result = String.join(", ", filmGenres);
        }
        return result;
    }

    public void setFilmGenres(ArrayList<String> filmGenres) {
        this.filmGenres = filmGenres;
    }

    public ArrayList<String> getFilmCountries() {
        return filmCountries;
    }

    public String getFilmCountriesInString() {
        String result = null;
        if(filmCountries != null) {
            result = String.join(", ", filmCountries);
        }
        return result;
    }

    public void setFilmCountries(ArrayList<String> filmCountries) {
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

    public Integer getNumberPositiveReviews() {
        return numberPositiveReviews;
    }

    public void setNumberPositiveReviews(Integer numberPositiveReviews) {
        this.numberPositiveReviews = numberPositiveReviews;
    }

    public MPAAType getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(MPAAType mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public AgeType getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(AgeType ageRating) {
        this.ageRating = ageRating;
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

    public String getFilmName() {
        if (filmNameRu != null) return filmNameRu;
        else return filmNameEn;
    }

    public void setBoxOffice(Long boxOffice) {
        this.boxOffice = boxOffice;
    }

    public boolean isDVDAvailable() {
        return isDVDAvailable;
    }

    public void setDVDAvailable(boolean DVDAvailable) {
        isDVDAvailable = DVDAvailable;
    }

    public boolean isBluRayAvailable() {
        return isBluRayAvailable;
    }

    public void setBluRayAvailable(boolean bluRayAvailable) {
        isBluRayAvailable = bluRayAvailable;
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
                isDVDAvailable() == film.isDVDAvailable() &&
                isBluRayAvailable() == film.isBluRayAvailable() &&
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
                getFilmType() == film.getFilmType() &&
                Objects.equals(getFilmGenres(), film.getFilmGenres()) &&
                Objects.equals(getFilmCountries(), film.getFilmCountries()) &&
                Objects.equals(getYear(), film.getYear()) &&
                Objects.equals(getKinopoiskRating(), film.getKinopoiskRating()) &&
                Objects.equals(getImdbRating(), film.getImdbRating()) &&
                Objects.equals(getWorldwideCriticsPercentRating(), film.getWorldwideCriticsPercentRating()) &&
                Objects.equals(getNumberPositiveReviews(), film.getNumberPositiveReviews()) &&
                getMpaaRating() == film.getMpaaRating() &&
                getAgeRating() == film.getAgeRating() &&
                Objects.equals(getBudget(), film.getBudget()) &&
                Objects.equals(getBoxOffice(), film.getBoxOffice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFilmID(), getFilmURL(), getFilmNameEn(), getFilmNameRu(), getFilmLentgh(), getFilmDescription(),
                getFilmSlogan(), getFilmPremiereDVD(), getFilmPremiereBluRay(), isHasAwards(), getFilmType(), getFilmGenres(),
                getFilmCountries(), getYear(), getKinopoiskRating(), getImdbRating(), getWorldwideCriticsPercentRating(),
                getNumberPositiveReviews(), getMpaaRating(), getAgeRating(), getBudget(), getBoxOffice(), isDVDAvailable(),
                isBluRayAvailable(), is3DAvailable());
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
                ", numberPositiveReviews=" + numberPositiveReviews +
                ", mpaaRating=" + mpaaRating +
                ", ageRating=" + ageRating +
                ", budget=" + budget +
                ", boxOffice=" + boxOffice +
                ", isDVDAvailable=" + isDVDAvailable +
                ", isBluRayAvailable=" + isBluRayAvailable +
                ", is3DAvailable=" + is3DAvailable +
                '}';
    }

    public boolean isFilmNameRuContains(String filmNameRu) {
        return Pattern.compile(Pattern.quote(filmNameRu), Pattern.CASE_INSENSITIVE).matcher(filmNameRu).find();
    }

    public boolean hasGenre(String genre) {
        return filmGenres.contains(genre);
    }

    public boolean hasCountry(String country) {
        return filmCountries.contains(country);
    }

    public boolean isBudgetInBoundaries(long minInMillionsDollars, long maxInMillionsDollars) {
        return budget >= minInMillionsDollars * 1000_000 && budget <= maxInMillionsDollars * 1000_000;
    }

    public boolean isPositiveReviewPercentInBoundaries(int min, int max) {
        return numberPositiveReviews >= min && numberPositiveReviews <= max;
    }

    public boolean isCriticsRateInBoundaries(int minRate, int maxRate) {
        return worldwideCriticsPercentRating >= minRate && worldwideCriticsPercentRating <= maxRate;
    }

    public boolean isIMDbRateInBoundaries(double minRate, double maxRate) {
        return imdbRating >= minRate && imdbRating <= maxRate;
    }

    public boolean isYearInBoundaries(int minYear, int maxYear) {
        Integer filmYear = Integer.parseInt(year);
        return filmYear >= minYear && filmYear <= maxYear;
    }

    public boolean isKinopoiskRatingInBoundaries(double minRate, double maxRate) {
        return getKinopoiskRating() >= minRate && getKinopoiskRating() <= maxRate;
    }

    public boolean isBoxofficeInBoundaries(int min, int max) {
        return getBoxOffice() >= (min * 1_000_000) && getBoxOffice() <= (max * 1_000_000);
    }

}
