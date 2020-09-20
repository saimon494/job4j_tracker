package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class Tracker {
    private final List<Item> items = new ArrayList<>();
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items.add(item);
        return item;
    }

    public List<Item> findAll() {
        return items;
    }

    public List<Item> findByName(String key) {
        List<Item> itemsNames = new ArrayList<>();
//        int index = 0;
        for (Item item : items) {
            if (item.getName() != null && item.getName().equals(key)) {
                itemsNames.add(item);
//                index++;
            }
        }
        return itemsNames;
    }

    private int indexOf(int id) {
        int rsl = -1;

        for (Item item : items) {
            if (item.getId() == id) {
                rsl = items.indexOf(item);
                break;
            }
        }
        return rsl;
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }

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
}