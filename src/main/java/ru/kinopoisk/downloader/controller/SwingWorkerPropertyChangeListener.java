package ru.kinopoisk.downloader.controller;

import javax.swing.*;

public interface SwingWorkerPropertyChangeListener {
    void attachPropertyChangeListener(SwingWorker<?, ?> sw);

    void detachPropertyChangeListener(SwingWorker<?, ?> sw);

}