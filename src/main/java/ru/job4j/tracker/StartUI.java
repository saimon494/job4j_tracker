package ru.job4j.tracker;

import java.util.Arrays;

public class StartUI {
    public static void main(String[] args) {
        Item item = new Item();
        item.setName("Vasya");
        Item item1 = new Item();
        item1.setName("Vova");
        Tracker tracker = new Tracker();
        tracker.add(item);
        tracker.add(item1);
        Item id = tracker.findById(2);
        System.out.println(id);
        Item[] name = tracker.findByName("Vasya");
        System.out.println(Arrays.toString(name));
        Item[] allItem = tracker.findAll();
        System.out.println(Arrays.toString(allItem));

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
//        String currentDateTimeFormat = item.getCreated().format(formatter);
//        System.out.println(currentDateTimeFormat);
    }
}
