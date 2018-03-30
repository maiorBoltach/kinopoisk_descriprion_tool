package ru.kinopoisk.downloader.controller;

import javax.swing.*;

public interface SwingWorkerPropertyChangeListener {
    public abstract void attachPropertyChangeListener(SwingWorker<?, ?> sw);
    public abstract void detachPropertyChangeListener(SwingWorker<?, ?> sw);

}