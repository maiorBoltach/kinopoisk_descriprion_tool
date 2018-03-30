package ru.kinopoisk.downloader.controller;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SwingWorkerProgressModel extends DefaultBoundedRangeModel
        implements SwingWorkerPropertyChangeListener {

    private static final long serialVersionUID = 2652024572507645835L;
    private PropertyChangeListener swingWorkerAdapter = new SwingWorkerPropertyChangeListenerAdapter();

    @Override
    public void attachPropertyChangeListener(SwingWorker<?, ?> swingWorker) {
        swingWorker.addPropertyChangeListener(swingWorkerAdapter);
    }

    @Override
    public void detachPropertyChangeListener(SwingWorker<?, ?> swingWorker) {
        swingWorker.removePropertyChangeListener(swingWorkerAdapter);
    }

    private class SwingWorkerPropertyChangeListenerAdapter implements
            PropertyChangeListener {

        public void propertyChange(PropertyChangeEvent evt) {
            SwingWorker<?, ?> swingWorker = (SwingWorker<?, ?>) evt.getSource();
            setValueIsAdjusting(true);
            setMinimum(0);
            setMaximum(100);
            setValue(swingWorker.getProgress());
            setValueIsAdjusting(false);
        }
    }
}

