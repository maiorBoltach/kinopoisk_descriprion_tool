package ru.kinopoisk.downloader.controller;

import ru.kinopoisk.downloader.data.Film;
import ru.kinopoisk.downloader.model.list.ListAdapterListModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.HashSet;

public class LoadFilmsExampleAction extends AbstractAction {

    private static final long serialVersionUID = 2636985714796751517L;

    private ListAdapterListModel<Film> filmListModel;
    private Collection<SwingWorkerPropertyChangeListener> swingWorkerPropertyChangeListeners = new HashSet<>();

    public LoadFilmsExampleAction(ListAdapterListModel<Film> filmListModel) {
        this.filmListModel = filmListModel;
        putValue(NAME, "Load Example");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke('E'));
    }

    public void actionPerformed(ActionEvent e) {
        LoadFilmsExampleWorker loadFilmsWorker = new LoadFilmsExampleWorker(filmListModel);
        for (SwingWorkerPropertyChangeListener swingWorkerPropertyChangeListener : swingWorkerPropertyChangeListeners) {
            swingWorkerPropertyChangeListener.attachPropertyChangeListener(loadFilmsWorker);
        }
        loadFilmsWorker.execute();
    }

    public void addSwingWorkerPropertyChangeListener(
            SwingWorkerPropertyChangeListener swingWorkerPropertyChangeListener) {
        swingWorkerPropertyChangeListeners.add(swingWorkerPropertyChangeListener);
    }

    public void removeSwingWorkerPropertyChangeListener(
            SwingWorkerPropertyChangeListener swingWorkerPropertyChangeListener) {
        swingWorkerPropertyChangeListeners.remove(swingWorkerPropertyChangeListener);
    }
}
