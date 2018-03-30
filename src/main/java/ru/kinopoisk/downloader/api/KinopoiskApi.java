package ru.kinopoisk.downloader.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import ru.kinopoisk.downloader.api.enums.KinopoiskOperations;
import ru.kinopoisk.downloader.data.Film;
import ru.kinopoisk.downloader.logger.LoggerClass;

import static ru.kinopoisk.downloader.api.JsonParser.getCurrentFilm;

public class KinopoiskApi {

    public KinopoiskApi() {}

    public Film getFilmInfo(int filmID) {
        String parameterFilmID = "?filmID=";
        String fullAPIString = KinopoiskOperations.FILM_DETAILS.toString() + parameterFilmID + String.valueOf(filmID);
        new ApiRequester();
        JsonNode jsonObject = ApiRequester.getInstance().getRequest(fullAPIString);
        checkJsonObject(jsonObject, filmID);
        Film currentFilm = null;
        try {
            currentFilm = getCurrentFilm(jsonObject);
            LoggerClass.getInstanceSummaryLogger().info("Parsed film: " + currentFilm);
        } catch (JsonProcessingException e) {
            LoggerClass.getInstanceSummaryLogger().error("Parsing film '" + filmID + "': " + e.getMessage());
            LoggerClass.getInstanceSummaryLoggerID().info(filmID);
        }
        return currentFilm;
    }

    private void checkJsonObject(JsonNode jsonObject, int id) {
        if (jsonObject == null) {
            LoggerClass.getInstanceSummaryLogger().info("Null JSON (id = '" + id + "')");
            LoggerClass.getInstanceSummaryLoggerID().info(id);
        }
    }
}
