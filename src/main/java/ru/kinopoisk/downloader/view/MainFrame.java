package ru.kinopoisk.downloader.view;

import ru.kinopoisk.downloader.controller.ClearListModelAction;
import ru.kinopoisk.downloader.controller.LoadFilmsAction;
import ru.kinopoisk.downloader.controller.LoadFilmsExampleAction;
import ru.kinopoisk.downloader.controller.SwingWorkerProgressModel;
import ru.kinopoisk.downloader.data.Film;
import ru.kinopoisk.downloader.model.list.ListAdapterListModel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 4353611743416911021L;

    private ListAdapterListModel<Film> filmListModel = new ListAdapterListModel<>();

    private SwingWorkerProgressModel swingWorkerProgressModel = new SwingWorkerProgressModel();

    private OverviewPanel overviewPanel = new OverviewPanel();

    private Component currentContent;

    public MainFrame() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        overviewPanel.setFilmList(filmListModel);
        setContent(overviewPanel);

        JMenuBar jMenuBar = new JMenuBar();
        setJMenuBar(jMenuBar);
        initMenu(jMenuBar);

        setSize(640, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kinopoisk Film Information Download Tool");
        setLocationRelativeTo(null);
    }

    private void initMenu(JMenuBar jMenuBar) {
        initFileMenu(jMenuBar);
    }

    private void initFileMenu(JMenuBar jMenuBar) {
        JMenu fileMenu = new JMenu("Films");
        jMenuBar.add(fileMenu);

        LoadFilmsAction loadFilmsAction = new LoadFilmsAction(filmListModel);
        loadFilmsAction.addSwingWorkerPropertyChangeListener(swingWorkerProgressModel);
        JMenuItem loadMenuItem = new JMenuItem(loadFilmsAction);
        fileMenu.add(loadMenuItem);


        LoadFilmsExampleAction loadFilmsExampleAction = new LoadFilmsExampleAction(filmListModel);
        loadFilmsExampleAction.addSwingWorkerPropertyChangeListener(swingWorkerProgressModel);

        JMenuItem loadMenuItemExample = new JMenuItem(loadFilmsExampleAction);
        fileMenu.add(loadMenuItemExample);

        ClearListModelAction clearFilmsModelAction = new ClearListModelAction(filmListModel);
        JMenuItem clearFilmsMenuItem = new JMenuItem(clearFilmsModelAction);
        fileMenu.add(clearFilmsMenuItem);
    }

    public void setContent(Component component) {
        Container contentPane = getContentPane();
        if (currentContent != null) {
            contentPane.remove(currentContent);
        }
        contentPane.add(component, BorderLayout.CENTER);
        currentContent = component;
        contentPane.doLayout();
        repaint();
    }

}

