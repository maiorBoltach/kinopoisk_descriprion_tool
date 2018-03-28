package ru.kinopoisk.api;


import com.fasterxml.jackson.databind.JsonNode;
import ru.kinopoisk.api.models.Film;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

public class Runner {
    private static final String PATH = "src/main/resources/IDTemp.ini";

    public static void main(String[] args) throws Exception {
        execute(444);
    }

    private static void IDreader() throws Exception {
        File file = new File(PATH);
        FileInputStream fileInputStream = new FileInputStream((file));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            try {
                int ID = Integer.parseInt(line);
                if (ID <= 0) {
                    throw new IOException("wrong ID(<=0)");
                }
                System.out.println(ID);
                execute(ID);
            } catch (IOException e) {
                LoggerClass.getInstanceSummaryLogger().error(e.getMessage());
            } catch (NumberFormatException e) {
                LoggerClass.getInstanceSummaryLogger().error(e.getMessage());
            }
        }
    }

    private static void execute(int ID) {
        APIRequester api = new APIRequester();
        JsonNode getFilmInfo = api.getFilmInfo(ID);
        System.out.println(getFilmInfo);
        JsonParser newParser = new JsonParser();
        Film current = newParser.getCurrentFilm(getFilmInfo);
        System.out.println(current);
        DbHandler dbHandler = null;
        try {
            dbHandler = DbHandler.getInstance();
            dbHandler.addFilm(current);
            List<Film> products = dbHandler.getAllFilms();
            for (Film films : products) {
                System.out.println(films.toString());
            }
        } catch (SQLException e) {
            LoggerClass.getInstanceSummaryLogger().error(e.getMessage());
        }
    }
}