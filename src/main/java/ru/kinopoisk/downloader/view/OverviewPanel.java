package ru.kinopoisk.downloader.view;

import ru.kinopoisk.downloader.data.Film;
import ru.kinopoisk.downloader.model.list.ListAdapterListModel;
import ru.kinopoisk.downloader.model.table.FilmTableModel;

import javax.swing.*;

public class OverviewPanel extends JPanel {

    private static final long serialVersionUID = -4471606875093169644L;
    private FilmTableModel personTableModel = new FilmTableModel();
    private ListAdapterListModel<Film> personListModel = new ListAdapterListModel<Film>();

    private JTable personTable = new JTable(personTableModel);
    private JList<Film> personList = new JList<Film>(personListModel);

    public void setPersonList(ListAdapterListModel<Film> personListModel) {
        personList.setModel(personListModel);
        personTableModel.setListModel(personListModel);
    }

    public OverviewPanel() {
        setLayout(null);
        personTable.setSelectionModel(personList.getSelectionModel());
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 580, 130);
        add(scrollPane);
        scrollPane.setViewportView(personTable);
    }



}

