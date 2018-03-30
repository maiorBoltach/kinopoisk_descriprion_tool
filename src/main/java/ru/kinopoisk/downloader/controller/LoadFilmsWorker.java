package ru.kinopoisk.downloader.controller;

import ru.kinopoisk.downloader.api.KinopoiskApi;
import ru.kinopoisk.downloader.data.Film;
import ru.kinopoisk.downloader.logger.LoggerClass;
import ru.kinopoisk.downloader.model.list.ListAdapterListModel;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoadFilmsWorker extends SwingWorker<List<Film>, Film> {

    private volatile int maxProgress;
    private int progressedItems;
    private ListAdapterListModel<Film> filmListModel;
    private File file;

    public LoadFilmsWorker(ListAdapterListModel<Film> filmListModel, File file) {
        this.filmListModel = filmListModel;
        this.file = file;
        int lines = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (reader.readLine() != null) lines++;
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        maxProgress = lines;
    }

    @Override
    protected List<Film> doInBackground() throws Exception {
        LoggerClass.getInstanceSummaryLogger().info("Start executing");
        filmListModel.clear();
        List<Film> films = new ArrayList<Film>();

        KinopoiskApi api = new KinopoiskApi();
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
                    Film film = api.getFilmInfo(id);
                    if(film != null)
                    {
                        films.add(film);
                        publish(film);
                    }
                } catch (IOException | NumberFormatException e) {
                    LoggerClass.getInstanceSummaryLogger().error(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            LoggerClass.getInstanceSummaryLogger().error(e.getMessage());
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
        return (int) (100.0 / (double) maxProgress * (double) progressedItems);
    }
}
