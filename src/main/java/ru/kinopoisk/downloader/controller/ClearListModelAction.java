package ru.kinopoisk.downloader.controller;

import ru.kinopoisk.downloader.model.list.ListAdapterListModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ClearListModelAction extends AbstractAction {

    private static final long serialVersionUID = 2914235274602131249L;

    private ListAdapterListModel<?> listModel;

    public ClearListModelAction(ListAdapterListModel<?> listModel) {
        this.listModel = listModel;

        putValue(NAME, "Clear");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke('C'));
    }

    public void actionPerformed(ActionEvent e) {
        listModel.clear();
    }

}
