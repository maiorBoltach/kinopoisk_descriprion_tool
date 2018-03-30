package ru.kinopoisk.downloader.controller;

import ru.kinopoisk.downloader.data.Film;
import ru.kinopoisk.downloader.logger.LoggerClass;
import ru.kinopoisk.downloader.model.list.ListAdapterListModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Collection;
import java.util.HashSet;

public class LoadFilmsAction extends AbstractAction {

    private static final long serialVersionUID = 2636985714796751517L;

    private ListAdapterListModel<Film> filmListModel;
    private Collection<SwingWorkerPropertyChangeListener> swingWorkerPropertyChangeListeners = new HashSet<SwingWorkerPropertyChangeListener>();

    public LoadFilmsAction(ListAdapterListModel<Film> filmListModel) {
        this.filmListModel = filmListModel;
        putValue(NAME, "Load");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke('L'));
    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int status = fileChooser.showOpenDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            LoggerClass.getInstanceSummaryLogger().info("Opening: " + file.getName() + ".");
            LoadFilmsWorker loadFilmsWorker = new LoadFilmsWorker(filmListModel, file);
            for (SwingWorkerPropertyChangeListener swingWorkerPropertyChangeListener : swingWorkerPropertyChangeListeners) {
                swingWorkerPropertyChangeListener.attachPropertyChangeListener(loadFilmsWorker);
            }
            loadFilmsWorker.execute();
        } else {
            LoggerClass.getInstanceSummaryLogger().info("Open command cancelled by user.");
        }
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
