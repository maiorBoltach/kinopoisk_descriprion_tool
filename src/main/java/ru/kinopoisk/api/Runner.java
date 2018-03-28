package ru.kinopoisk.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import ru.kinopoisk.api.models.Film;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

public class Runner {
    private static final String PATH = "src/main/resources/films id/1968 - 1977.ini";
    private static final String YEAR_FROM = "1968";
    private static final String YEAR_TO = "1977";

    public static void main(String[] args) throws IOException {
        LoggerClass.getInstanceSummaryLogger().info("Start executing");

        /*File idCatalogFile = downloadIdInYearInterval(YEAR_FROM, YEAR_TO);
        idReader(idCatalogFile);*/
        execute(444);

        LoggerClass.getInstanceSummaryLogger().info("Program is stopped");
    }

    private static void idReader(File file) throws IOException {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    LoggerClass.getInstanceSummaryLogger().trace("Executing film id = " + line);
                    int id = Integer.parseInt(line);
                    if (id <= 0) {
                        throw new IOException("Wrong ID '" + id + "' (<= 0)");
                    }
                    execute(id);
                } catch (IOException | NumberFormatException e) {
                    LoggerClass.getInstanceSummaryLogger().error(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            LoggerClass.getInstanceSummaryLogger().error(e.getMessage());
        }
    }

    private static void execute(int id) {
        APIRequester api = new APIRequester();
        JsonNode filmInfoNode = api.getFilmInfo(id);
        if (filmInfoNode != null) {
            JsonParser newParser = new JsonParser();
            Film currentFilm = null;
            try {
                currentFilm = newParser.getCurrentFilm(filmInfoNode);
                LoggerClass.getInstanceSummaryLogger().info("Parsed film: " + currentFilm);
            } catch (JsonProcessingException e) {
                LoggerClass.getInstanceSummaryLogger().error("Parsing film '" + id + "': " + e.getMessage());
                LoggerClass.getInstanceSummaryLoggerID().info(id);
            }
            DbHandler dbHandler = null;
            try {
                dbHandler = DbHandler.getInstance();
                dbHandler.addFilm(currentFilm);
                List<Film> products = dbHandler.getAllFilms();
                for (Film films : products) {
                    System.out.println(films.toString());
                }
            } catch (SQLException e) {
                LoggerClass.getInstanceSummaryLogger().error(e.getMessage());
                LoggerClass.getInstanceSummaryLoggerID().info(id);
            }
        }
    }
}