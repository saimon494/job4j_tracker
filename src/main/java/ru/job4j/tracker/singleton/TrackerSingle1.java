package ru.job4j.tracker.singleton;

import ru.job4j.tracker.Tracker;

public enum TrackerSingle1 {
    INSTANCE;

    private static Tracker tracker;

    public Tracker getTracker() {
        return tracker;
    }
}

