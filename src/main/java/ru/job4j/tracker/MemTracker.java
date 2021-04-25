package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class MemTracker implements Store {

    private final List<Item> items = new ArrayList<>();
    private int ids = 1;
    private int size = 0;

    @Override
    public Item add(Item item) {
        item.setId(ids++);
        items.add(item);
        size++;
        return item;
    }

    @Override
    public List<Item> findAll() {
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> itemsNames = new ArrayList<>();
        for (Item item : items) {
            if (item.getName() != null && item.getName().equals(key)) {
                itemsNames.add(item);
            }
        }
        return itemsNames;
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < size; index++) {
            if (items.get(index).getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = false;
        int index = indexOf(id);
        item.setId(id);
        if (index != -1) {
            rsl = true;
            items.set(index, item);
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = false;
        int index = indexOf(id);
        if (index != -1) {
            rsl = true;
            items.remove(index);
            size--;
        }
        return rsl;
    }

    @Override
    public void init() {
    }

    @Override
    public void close() {
    }
}