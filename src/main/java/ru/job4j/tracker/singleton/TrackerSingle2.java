package ru.job4j.tracker.singleton;

import ru.job4j.tracker.MemTracker;

public class TrackerSingle2 {
    private static TrackerSingle2 instance;

    private final MemTracker memTracker = new MemTracker();

    private TrackerSingle2() {
    }

    public MemTracker getTracker() {
        return memTracker;
    }

    public static TrackerSingle2 getInstance() {
        if (instance == null) {
            instance = new TrackerSingle2();
        }
        return instance;
    }

}
