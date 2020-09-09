package ru.job4j.tracker;

import java.lang.reflect.Array;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class StartUI {
    public static void main(String[] args) {
        Item item = new Item();
        item.setName("Vasya");
        Tracker tracker = new Tracker();
        tracker.add(item);
        Item id = tracker.findById(1);
        System.out.println(id);

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
//        String currentDateTimeFormat = item.getCreated().format(formatter);
//        System.out.println(currentDateTimeFormat);
    }
}
