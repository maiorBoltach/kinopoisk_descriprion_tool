package ru.kinopoisk.api;

import org.sqlite.JDBC;
import ru.kinopoisk.api.models.Creators;
import ru.kinopoisk.api.models.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DbHandler {

    // Константа, в которой хранится адрес подключения
    private static final String DATABASE_URL = "jdbc:sqlite:2008 - 2017.db";
    private Connection connection;
    private static DbHandler instance = null;

    public static synchronized DbHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }

    private DbHandler() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection(DATABASE_URL);
    }

    public List<Film> getAllFilms() {
        int lastSuccessfulID=0;
        try (Statement statement = this.connection.createStatement()) {
            List<Film> products = new ArrayList<Film>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM filmInfo");
            while (resultSet.next()) {
                Film currentFilm = new Film();
                currentFilm.setFilmID(resultSet.getInt("filmID"));
                lastSuccessfulID=resultSet.getInt("filmID");
                currentFilm.setFilmNameRu(resultSet.getString("nameRU"));
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
            statement.setObject(2, film.getFilmNameRu());
            statement.setObject(3, film.getFilmNameEn());
            statement.setObject(4, film.getFilmURL());
            statement.setObject(5, film.isHasAwards() ? 1 : 0);
            statement.setObject(6, film.getKinopoiskRating());
            statement.setObject(7, film.getWorldwideCriticsPercentRating());
            statement.setObject(8, film.getImdbRating());
            statement.setObject(9, film.getFilmPremiereBluRay());
            statement.setObject(10, film.getFilmPremiereDVD());
            statement.setObject(11, film.getFilmSlogan());
            statement.setObject(12, film.getFilmCountries());
            statement.setObject(13, film.getFilmDescription());
            statement.setObject(14, film.getFilmLentgh());
            statement.setObject(15, film.getFilmGenres());
            statement.setObject(16, film.is3DAvailable() ? 1 : 0);
            statement.setObject(17, film.getYear());
            statement.setObject(18, film.getBudget());
            statement.setObject(19, film.getBoxOffice());
            statement.setObject(20, film.getFilmType());
            statement.execute();
        } catch (SQLException e) {
            LoggerClass.getInstanceSummaryLogger().error(e.getMessage());
        }

        for (Creators current : film.getFilmCreators()) {
            try (PreparedStatement statement = this.connection.prepareStatement(
                    "INSERT INTO creatorInfo(`creatorID`, `nameRU`, `nameEN`, `posterURL`) " +
                            "VALUES(?, ?, ?, ?)")) {
                statement.setObject(1, current.getId());
                statement.setObject(2, current.getNameRu());
                statement.setObject(3, current.getNameEn());
                statement.setObject(4, current.getPosterUrl());
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