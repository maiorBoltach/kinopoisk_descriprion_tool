package ru.kinopoisk.downloader.controller;

import ru.kinopoisk.downloader.data.Film;
import ru.kinopoisk.downloader.model.list.ListAdapterListModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.HashSet;

public class LoadFilmsAction extends AbstractAction {

    private static final long serialVersionUID = 2636985714796751517L;

    private ListAdapterListModel<Film> personListModel;
    private Collection<SwingWorkerPropertyChangeListener> swingWorkerPropertyChangeListeners = new HashSet<SwingWorkerPropertyChangeListener>();

    public LoadFilmsAction(ListAdapterListModel<Film> personListModel) {
        this.personListModel = personListModel;
        putValue(NAME, "Load");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke('L'));
    }

    public void actionPerformed(ActionEvent e) {
        LoadFilmsWorker loadFilmsWorker = new LoadFilmsWorker(personListModel);
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
