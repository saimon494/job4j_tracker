package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item[] findAll() {
        return Arrays.copyOf(items, size);
    }

    public Item[] findByName(String key) {
        Item[] itemsNames = new Item[this.size];
        int cap = 0;
        for (int i = 0; i < this.size; i++) {
            Item item = items[i];
            if (item.getName() != null && item.getName().equals(key)) {
                itemsNames[cap] = item;
                cap++;
            }
        }
        return Arrays.copyOf(itemsNames, cap);
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < size; index++) {
            if (items[index].getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items[index] : null;
    }

    public boolean replace(int id, Item item) {
        boolean rsl = false;
        int index = indexOf(id);
        item.setId(id);
        if (index != -1) {
            items[index] = item;
            rsl = true;
        }
        return rsl;
    }
}