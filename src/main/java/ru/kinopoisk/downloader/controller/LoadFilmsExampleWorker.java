package ru.kinopoisk.downloader.controller;

import ru.kinopoisk.downloader.api.KinopoiskApi;
import ru.kinopoisk.downloader.data.Film;
import ru.kinopoisk.downloader.logger.LoggerClass;
import ru.kinopoisk.downloader.model.list.ListAdapterListModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class LoadFilmsExampleWorker extends SwingWorker<List<Film>, Film> {

    private volatile int maxProgress;
    private int progressedItems;
    private ListAdapterListModel<Film> filmListModel;

    public LoadFilmsExampleWorker(ListAdapterListModel<Film> filmListModel) {
        this.filmListModel = filmListModel;
    }

    @Override
    protected List<Film> doInBackground() {
        LoggerClass.getInstanceSummaryLogger().info("Start executing");
        filmListModel.clear();
        maxProgress = 3;
        List<Film> films = new ArrayList<>();
        KinopoiskApi api = new KinopoiskApi();
        Film film1 = api.getFilmInfo(444);
        if (film1 != null) {
            films.add(film1);
            publish(film1);
        }

        Film film2 = api.getFilmInfo(666);
        if (film2 != null) {
            films.add(film2);
            publish(film2);
        }
        Film film3 = api.getFilmInfo(667);
        if (film3 != null) {
            films.add(film3);
            publish(film3);
        }
        LoggerClass.getInstanceSummaryLogger().info("Finished executing");
        return films;
    }

    @Override
    protected void process(List<Film> chunks) {
        filmListModel.addAll(chunks);
        progressedItems = progressedItems + chunks.size();
        setProgress(calcProgress(progressedItems));
    }

    private int calcProgress(int progressedItems) {
        return (int) ((100.0 / (double) maxProgress) * (double) progressedItems);
    }
}
