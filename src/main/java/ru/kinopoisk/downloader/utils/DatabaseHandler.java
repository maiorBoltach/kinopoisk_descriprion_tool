package ru.kinopoisk.downloader.utils;

import org.sqlite.JDBC;
import ru.kinopoisk.downloader.data.Creator;
import ru.kinopoisk.downloader.data.Film;
import ru.kinopoisk.downloader.logger.LoggerClass;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseHandler {

    private static final String DATABASE_URL = "jdbc:sqlite:2008 - 2017.db";
    private static DatabaseHandler instance = null;
    private Connection connection;

    private DatabaseHandler() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection(DATABASE_URL);
    }

    public static synchronized DatabaseHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DatabaseHandler();
        return instance;
    }

    public List<Film> getAllFilms() {
        try (Statement statement = this.connection.createStatement()) {
            List<Film> products = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM filmInfo");
            while (resultSet.next()) {
                Film currentFilm = new Film();
                currentFilm.setFilmID(resultSet.getInt("filmID"));
                currentFilm.setNameRU(resultSet.getString("nameRU"));
                currentFilm.setYear(resultSet.getString("year"));
                products.add(currentFilm);
            }
            return products;

        } catch (SQLException e) {
            LoggerClass.getInstanceSummaryLogger().error(e.getMessage());
            return Collections.emptyList();
        }
    }

    public void addFilm(Film film) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO filmInfo(`filmID`, `nameRU`, `nameEN`, `webURL`, " +
                        "`hasAwards`, `kinopoiskRating`, `filmCriticsRating`, `imdbRating`," +
                        "`premiereBluRay`, `premiereDVD`, `slogan`, `country`," +
                        "`description`, `length`, `genre`, `is3D`," +
                        "`year`, `budget`, `worldwideBoxOffice`, `filmType`) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setObject(1, film.getFilmID());
            statement.setObject(2, film.getNameRU());
            statement.setObject(3, film.getNameEN());
            statement.setObject(4, film.getWebURL());
            statement.setObject(5, film.isHasAwards() ? 1 : 0);
            statement.setObject(6, film.getRatingData().getRating());
            statement.setObject(7, film.getRatingData().getRatingFilmCritics());
            statement.setObject(8, film.getRatingData().getRatingIMDb());
            statement.setObject(9, film.getRentData().getPremiereBluRay());
            statement.setObject(10, film.getRentData().getPremiereDVD());
            statement.setObject(11, film.getSlogan());
            statement.setObject(12, film.getCountry());
            statement.setObject(13, film.getDescription());
            statement.setObject(14, film.getFilmLength());
            statement.setObject(15, film.getGenre());
            statement.setObject(16, film.isIs3D() ? 1 : 0);
            statement.setObject(17, film.getYear());
            statement.setObject(18, film.getBudgetData().getBudget());
            statement.setObject(19, film.getBudgetData().getGrossWorld());
            statement.setObject(20, film.getType());
            statement.execute();
        } catch (SQLException e) {
            LoggerClass.getInstanceSummaryLogger().error(e.getMessage());
        }

        for (Creator current : film.getCreators()) {
            try (PreparedStatement statement = this.connection.prepareStatement(
                    "INSERT INTO creatorInfo(`creatorID`, `nameRU`, `nameEN`, `posterURL`) " +
                            "VALUES(?, ?, ?, ?)")) {
                statement.setObject(1, current.getId());
                statement.setObject(2, current.getNameRU());
                statement.setObject(3, current.getNameEN());
                statement.setObject(4, current.getPosterURL());
                statement.execute();
            } catch (SQLException e) {
                LoggerClass.getInstanceSummaryLogger().error(e.getMessage());
            }

            try (PreparedStatement statement = this.connection.prepareStatement(
                    "INSERT INTO filmCreatorsInfo(`creatorID`, `professionKey`, `professionText`, `description`, `filmID`) " +
                            "VALUES(?, ?, ?, ?, ?)")) {
                statement.setObject(1, current.getId());
                statement.setObject(2, current.getProfessionKey());
                statement.setObject(3, current.getProfessionText());
                statement.setObject(4, current.getDescription());
                statement.setObject(5, film.getFilmID());
                statement.execute();
            } catch (SQLException e) {
                LoggerClass.getInstanceSummaryLogger().error(e.getMessage());
            }
        }


    }

    public void deleteFilm(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM filmInfo WHERE filmID = ?")) {
            statement.setObject(1, id);
            statement.execute();
        } catch (SQLException e) {
            LoggerClass.getInstanceSummaryLogger().error(e.getMessage());
        }
    }
}