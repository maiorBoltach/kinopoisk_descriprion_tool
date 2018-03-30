package ru.kinopoisk.downloader.model.table;

import ru.kinopoisk.downloader.data.Film;
import ru.kinopoisk.downloader.model.list.ListModelHolder;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.Map;

public class FilmTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1547542546403627396L;
    private ListModelHolder<Film> filmListModelHolder = new ListModelHolder<>();
    private ListModelChangeListener listModelChangeListener = new ListModelChangeListener();
    private Map<Column, String> columnDisplayNames = new HashMap<>();
    public FilmTableModel() {
        columnDisplayNames.put(Column.FILM_ID, "ID");
        columnDisplayNames.put(Column.NAME_RU, "Name (RU)");
        columnDisplayNames.put(Column.NAME_EN, "Name (EN)");
        columnDisplayNames.put(Column.YEAR, "Year");
        columnDisplayNames.put(Column.LENGTH, "Length");
        columnDisplayNames.put(Column.IMDB, "IMDB");
        filmListModelHolder.addListDataListeners(listModelChangeListener);
    }

    public final void setListModel(ListModel<Film> listModel) {
        filmListModelHolder.setModel(listModel);
    }

    public int getRowCount() {
        ListModel<Film> listModel = filmListModelHolder.getModel();
        return listModel.getSize();
    }

    public int getColumnCount() {
        return Column.values().length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Object columnValue = null;

        ListModel<Film> listModel = filmListModelHolder.getModel();
        Film film = listModel.getElementAt(rowIndex);
        Column column = getColumn(columnIndex);

        switch (column) {
            case FILM_ID:
                columnValue = film.getFilmID();
                break;
            case NAME_RU:
                columnValue = film.getNameRU();
                break;
            case NAME_EN:
                columnValue = film.getNameEN();
                break;
            case YEAR:
                columnValue = film.getYear();
                break;
            case LENGTH:
                columnValue = film.getFilmLength();
                break;
            case IMDB:
                columnValue = film.getRatingData().getRatingIMDb();
                break;
            default:
                break;
        }

        return columnValue;
    }

    private Column getColumn(int columnIndex) {
        Column[] columns = Column.values();
        return columns[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public String getColumnName(int column) {
        Column columnObj = getColumn(column);
        return columnDisplayNames.get(columnObj);
    }

    private enum Column {
        FILM_ID, NAME_RU, NAME_EN, YEAR, LENGTH, IMDB
    }

    private class ListModelChangeListener implements ListDataListener {
        public void intervalAdded(ListDataEvent e) {
            fireTableDataChanged();
        }

        public void intervalRemoved(ListDataEvent e) {
            fireTableDataChanged();
        }

        public void contentsChanged(ListDataEvent e) {
            fireTableDataChanged();
        }
    }
}
