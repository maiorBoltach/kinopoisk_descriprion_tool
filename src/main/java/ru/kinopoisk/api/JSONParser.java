package ru.kinopoisk.api;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.kinopoisk.api.models.Creators;
import ru.kinopoisk.api.models.Film;

import java.util.ArrayList;

import static ru.kinopoisk.api.models.Film.FilmType.MOVIE;
import static ru.kinopoisk.api.models.Film.FilmType.SERIAL;

public class JSONParser {
    private JSONObject JSONData;
    private int actualID;

    public Film getCurrentFilm(JSONObject JSONData) {

        setJSONData(JSONData);
        Film currentFilm = new Film();
        actualID = setUpIdCurrentFilm();
        currentFilm.setFilmID(actualID);
        currentFilm.setFilmURL(setUpFilmUrlCurrentFilm());
        currentFilm.setFilmNameRu(setUpNameRuCurrentFilm());
        currentFilm.setFilmNameEn(setUpNameEnCurrentFilm());
        currentFilm.setFilmLentgh(setUpFilmLentghCurrentFilm());
        currentFilm.setYear(setUpYearCurrentFilm());
        currentFilm.setFilmDescription(setUpFilmDescriptionCurrentFilm());
        currentFilm.setFilmSlogan(setUpFilmSloganCurrentFilm());
        currentFilm.setFilmPremiereDVD(setUpFilmPremiereDVDCurrentFilm());
        currentFilm.setFilmPremiereBluRay(setUpFilmPremiereBluRayCurrentFilm());
        currentFilm.setHasAwards(setUpHasAwardsCurrentFilm());
        currentFilm.setFilmType(setUpFilmTypeCurrentFilm());
        currentFilm.setFilmGenres(setUpFilmGenresCurrentFilm());
        currentFilm.setFilmCountries(setUpFilmCountriesCurrentFilm());
        currentFilm.setKinopoiskRating(setUpKinopoiskRatingCurrentFilm());
        currentFilm.setImdbRating(setUpImdbRatingCurrentFilm());
        currentFilm.setWorldwideCriticsPercentRating(setUpWorldWideCriticsPercentRatingCurrentFilm());
        currentFilm.setBudget(setUpBudgetCurrentFilm());
        currentFilm.setBoxOffice(setUpBoxOfficeCurrentFilm());
        currentFilm.setFilmCreators(setUpFilmCreatorsCurrentFilm());
        return currentFilm;

    }

    private void setJSONData(JSONObject JSONData) {
        this.JSONData = JSONData;
    }

    private JSONObject getCurrentJSONObject(String JSONVAriable, JSONObject data) {
        try {
            return data.getJSONObject(JSONVAriable);
        } catch (Exception e) {
            return null;
        }
    }


    @Nullable
    private JSONArray getCurrentJSONArray(String JSONVariable, JSONObject data) {
        try {
            return data.getJSONArray(JSONVariable);
        } catch (Exception e) {
            return null;
        }
    }

    @Nullable
    private String getCurrentString(String JSONVariable, JSONObject data) {
        try {
            return data.getString(JSONVariable);
        } catch (Exception e) {
            return null;
        }
    }

    @Nullable
    private Boolean getCurrentBoolean(String JSONVariable, JSONObject data) {
        try {
            return (data.getInt(JSONVariable) == 1);
        } catch (Exception e) {
            return false;
        }
    }

    @Nullable
    private Integer getCurrentInteger(String JSONVariable, JSONObject data) {
        try {
            return data.getInt(JSONVariable);
        } catch (Exception e) {
            return null;
        }
    }

    @Nullable
    private Long getCurrentLong(String JSONVariable, JSONObject data) {
        try {
            return data.getLong(JSONVariable);
        } catch (Exception e) {
            return null;
        }
    }

    @Nullable
    private Double getCurrentDouble(String JSONVariable, JSONObject data) {
        try {
            return data.getDouble(JSONVariable);
        } catch (Exception e) {
            return null;
        }
    }

    private String setUpNameRuCurrentFilm() {
        JSONObject data = getCurrentJSONObject("data", JSONData);
        return getCurrentString("nameRU", data);
    }

    private String setUpNameEnCurrentFilm() {
        JSONObject data = getCurrentJSONObject("data", JSONData);
        return getCurrentString("nameEN", data);
    }

    private String setUpYearCurrentFilm() {
        JSONObject data = getCurrentJSONObject("data", JSONData);
        return getCurrentString("year", data);
    }

    private int setUpIdCurrentFilm() {
        JSONObject data = getCurrentJSONObject("data", JSONData);
        return getCurrentInteger("filmID", data);
    }

    private String setUpFilmUrlCurrentFilm() {
        JSONObject data = getCurrentJSONObject("data", JSONData);
        return getCurrentString("webURL", data);
    }

    private String setUpFilmLentghCurrentFilm() {
        JSONObject data = getCurrentJSONObject("data", JSONData);
        return getCurrentString("filmLength", data);
    }

    private String setUpFilmDescriptionCurrentFilm() {
        JSONObject data = getCurrentJSONObject("data", JSONData);
        return getCurrentString("description", data);
    }

    private String setUpFilmSloganCurrentFilm() {
        JSONObject data = getCurrentJSONObject("data", JSONData);
        return getCurrentString("slogan", data);
    }

    private String setUpFilmPremiereDVDCurrentFilm() {
        JSONObject data = getCurrentJSONObject("data", JSONData);
        JSONObject premiere = getCurrentJSONObject("rentData", data);
        return getCurrentString("premiereDVD", premiere);
    }

    private String setUpFilmPremiereBluRayCurrentFilm() {
        JSONObject data = getCurrentJSONObject("data", JSONData);
        JSONObject premiere = getCurrentJSONObject("rentData", data);
        return getCurrentString("premiereBluRay", premiere);
    }

    private Boolean setUpHasAwardsCurrentFilm() {
        JSONObject data = getCurrentJSONObject("data", JSONData);
        return getCurrentBoolean("hasAwards", data);
    }

    private Film.FilmType setUpFilmTypeCurrentFilm() {
        JSONObject data = getCurrentJSONObject("data", JSONData);
        switch (getCurrentString("type", data)) {
            case "KPFilm":
                return MOVIE;
            case "KPSerial":
                return SERIAL;
            default:
                return MOVIE;
        }
    }

    private ArrayList<String> setUpFilmGenresCurrentFilm() {
        JSONObject data = getCurrentJSONObject("data", JSONData);
        ArrayList<String> formattedGenres = null;

        if (getCurrentString("genre", data) != null) {
            formattedGenres = new ArrayList<>();
            String[] genres = getCurrentString("genre", data).split(",");
            for (String genre : genres) {
                formattedGenres.add(genre.trim());
            }
        }
        return formattedGenres;
    }

    private ArrayList<String> setUpFilmCountriesCurrentFilm() {
        JSONObject data = getCurrentJSONObject("data", JSONData);
        ArrayList<String> formattedCountries = null;
        if (getCurrentString("country", data) != null) {
            formattedCountries = new ArrayList<String>();
            String[] countries = getCurrentString("country", data).split(",");
            for (String country : countries) {
                formattedCountries.add(country.trim());
            }
        }
        return formattedCountries;
    }

    private Double setUpKinopoiskRatingCurrentFilm() {
        JSONObject data = getCurrentJSONObject("data", JSONData);
        JSONObject raitingData = getCurrentJSONObject("ratingData", data);
        return (getCurrentString("rating", raitingData) != null) ? (Double.parseDouble(getCurrentString("rating", raitingData).replaceAll(" ", ""))) : null;
    }

    private Double setUpImdbRatingCurrentFilm() {
        JSONObject data = getCurrentJSONObject("data", JSONData);
        JSONObject raitingData = getCurrentJSONObject("ratingData", data);
        return getCurrentDouble("ratingIMDb", raitingData);
    }

    private Integer setUpWorldWideCriticsPercentRatingCurrentFilm() {
        JSONObject data = getCurrentJSONObject("data", JSONData);
        JSONObject raitingData = getCurrentJSONObject("ratingData", data);
        return (getCurrentString("ratingFilmCritics", raitingData) != null) ? (Integer.parseInt(getCurrentString("ratingFilmCritics", raitingData).replaceAll("%", ""))) : null;
    }

    private Long setUpBudgetCurrentFilm() {
        JSONObject data = getCurrentJSONObject("data", JSONData);
        JSONObject budgetData = getCurrentJSONObject("budgetData", data);
        return (getCurrentString("budget", budgetData)) != null ? (Long.parseLong(getCurrentString("budget", budgetData).replaceAll("\\D", ""))) : null;
    }

    private Long setUpBoxOfficeCurrentFilm() {
        JSONObject data = getCurrentJSONObject("data", JSONData);
        JSONObject budgetData = getCurrentJSONObject("budgetData", data);
        return (getCurrentString("grossWorld", budgetData)) != null ? (Long.parseLong(getCurrentString("grossWorld", budgetData).replaceAll("\\D", ""))) : null;
    }

    private ArrayList<Creators> setUpFilmCreatorsCurrentFilm() {
        JSONObject data = getCurrentJSONObject("data", JSONData);
        ArrayList<Creators> formattedCreators = new ArrayList<Creators>();
        JSONArray creators = getCurrentJSONArray("creators", data);
        for (int i = 0; i < (creators != null ? creators.length() : 0); i++) {
            Creators currentCreator = new Creators();
            JSONArray creatorArray = creators.getJSONArray(i);
            JSONObject creator = creatorArray.getJSONObject(0);
            currentCreator.setId(getCurrentInteger("id", creator));
            currentCreator.setNameRu(!getCurrentString("nameRU", creator).equals("") ? getCurrentString("nameRU", creator) : null);
            currentCreator.setNameEn(getCurrentString("nameEN", creator));
            currentCreator.setPosterUrl(getCurrentString("posterURL", creator));
            currentCreator.setProfessionText(getCurrentString("professionText", creator));
            currentCreator.setDescription(getCurrentString("description", creator));
            switch (getCurrentString("professionKey", creator)) {
                case "director":
                    currentCreator.setProfessionKey(Creators.creatorType.DIRECTOR);
                    break;
                case "actor":
                    currentCreator.setProfessionKey(Creators.creatorType.ACTOR);
                    break;
                case "producer":
                    currentCreator.setProfessionKey(Creators.creatorType.PRODUCER);
                    break;
                case "producer_ussr":
                    currentCreator.setProfessionKey(Creators.creatorType.PRODUCER);
                    break;
                default:
                    currentCreator.setProfessionKey(null);
            }
            formattedCreators.add(currentCreator);
        }
        return formattedCreators;
    }


}
