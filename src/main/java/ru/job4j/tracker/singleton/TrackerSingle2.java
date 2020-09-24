package ru.job4j.tracker.singleton;

import ru.job4j.tracker.Tracker;

public class TrackerSingle2 {
    private static TrackerSingle2 instance;

    private final Tracker tracker = new Tracker();

    private TrackerSingle2() {
    }

    public Tracker getTracker() {
        return tracker;
    }

    public static TrackerSingle2 getInstance() {
        if (instance == null) {
            instance = new TrackerSingle2();
        }
        return instance;
    }

}
