package ru.job4j.tracker.singleton;

import ru.job4j.tracker.Tracker;

public class TrackerSingle3 {
    private static final TrackerSingle3 INSTANCE = new TrackerSingle3();

    private final Tracker tracker = new Tracker();

    private TrackerSingle3() {
    }

    public Tracker getTracker() {
        return tracker;
    }

    public static TrackerSingle3 getInstance() {
        return INSTANCE;
    }
}

