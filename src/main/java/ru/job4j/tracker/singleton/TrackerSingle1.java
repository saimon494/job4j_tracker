package ru.job4j.tracker.singleton;

import ru.job4j.tracker.Tracker;

public enum TrackerSingle1 {
    INSTANCE;

    private final Tracker tracker = new Tracker();

    public Tracker getTracker() {
        return tracker;
    }
}

