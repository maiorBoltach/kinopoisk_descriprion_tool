package ru.kinopoisk.downloader.view;

import ru.kinopoisk.downloader.data.Creator;
import ru.kinopoisk.downloader.data.Film;
import ru.kinopoisk.downloader.model.list.ListAdapterListModel;
import ru.kinopoisk.downloader.model.table.FilmTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.stream.Collectors;

public class OverviewPanel extends JPanel {

    private static final long serialVersionUID = -4471606875093169644L;
    private FilmTableModel filmTableModel = new FilmTableModel();
    private ListAdapterListModel<Film> filmListModel = new ListAdapterListModel<Film>();

    private JTable filmTable = new JTable(filmTableModel);
    private JList<Film> filmList = new JList<Film>(filmListModel);

    public void setFilmList(ListAdapterListModel<Film> filmListModel) {
        filmList.setModel(filmListModel);
        filmTableModel.setListModel(filmListModel);
    }

    public OverviewPanel() {
        setLayout(new BorderLayout());
        filmTable.setSelectionModel(filmList.getSelectionModel());
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 580, 130);
        add(scrollPane,BorderLayout.CENTER);
        scrollPane.setViewportView(filmTable);
        filmTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && filmTable.getSelectedRow() != -1)
                    JOptionPane.showMessageDialog(null, getFilmFullInfo(filmList.getModel().getElementAt(filmTable.getSelectedRow())));
            }
        });
    }

    private String getFilmFullInfo(Film film) {
        StringBuilder result = new StringBuilder();
        result.append("ID - ").append(film.getFilmID()).append("\n");
        result.append("URL - ").append(film.getWebURL()).append("\n");
        result.append("\nGENERAL INFO").append("\n");
        result.append("Name (Russian) - ").append(film.getNameRU()).append("\n");
        result.append("Name (English) - ").append(film.getNameEN()).append("\n");
        result.append("Slogan - ").append(film.getSlogan()).append("\n");
        result.append("Type - ").append(film.getType()).append("\n");
        result.append("Year - ").append(film.getYear()).append("\n");
        result.append("Length - ").append(film.getFilmLength()).append("\n");
        result.append("Genre - ").append(film.getGenre()).append("\n");
        result.append("County - ").append(film.getCountry()).append("\n");
        result.append("Awards - ").append(film.isHasAwards()).append("\n");
        result.append("\nRATING").append("\n");
        result.append("IDBD - ").append(film.getRatingData().getRatingIMDb()).append("\n");
        result.append("Kinopoisk - ").append(film.getRatingData().getRating()).append("\n");
        result.append("Critics (positive) - ").append(film.getRatingData().getRatingFilmCritics()).append("%\n");
        result.append("\nPREMIERE").append("\n");
        result.append("DVD - ").append(film.getRentData().getPremiereDVD()).append("\n");
        result.append("Blu-Ray - ").append(film.getRentData().getPremiereBluRay()).append("\n");
        result.append("3D - ").append(film.isIs3D()).append("\n");
        result.append("\nBUDGET").append("\n");
        result.append("Budget - $").append(film.getBudgetData().getBudget()).append("\n");
        result.append("Gross (World) - $").append(film.getBudgetData().getGrossWorld()).append("\n");
        result.append("\nCREATORS").append("\n");
        result.append(film.getCreators().stream().map(Creator::getNameRU).collect(Collectors.joining(", "))).append("\n");

        return result.toString();
    }
}

