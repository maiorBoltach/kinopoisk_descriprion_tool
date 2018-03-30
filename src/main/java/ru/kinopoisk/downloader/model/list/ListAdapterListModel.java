package ru.kinopoisk.downloader.model.list;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListAdapterListModel<E> extends AbstractListModel<E> {

    private static final long serialVersionUID = -3348705533107354297L;

    private List<E> list = new ArrayList<E>();

    public void clear() {
        this.list.clear();
        fireListDataChanged();
    }

    private void fireListDataChanged() {
        fireContentsChanged(this, 0, Math.max(list.size() - 1, 0));
    }

    public void addAll(List<E> elements) {
        if (this.list.addAll(elements)) {
            fireIntervalAdded(elements.size());
        }
    }

    protected void fireIntervalAdded(int elementCOunt) {
        int index0 = list.size() - elementCOunt;
        fireIntervalAdded(this, Math.max(0, index0),
                Math.max(0, list.size() - 1));
    }

    public List<E> getList() {
        return Collections.unmodifiableList(list);
    }

    public void setList(List<E> list) {
        this.list.clear();
        this.list.addAll(list);
        fireListDataChanged();
    }

    public int getSize() {
        return list.size();
    }

    public E getElementAt(int index) {
        E elementAt = list.get(index);
        return elementAt;
    }

    public int indexOf(E element) {
        return list.indexOf(element);
    }

}
