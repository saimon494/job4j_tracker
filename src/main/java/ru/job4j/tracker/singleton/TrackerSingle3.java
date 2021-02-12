package ru.job4j.tracker.singleton;

import ru.job4j.tracker.MemTracker;

public class TrackerSingle3 {
    private static final TrackerSingle3 INSTANCE = new TrackerSingle3();

    private final MemTracker memTracker = new MemTracker();

    private TrackerSingle3() {
    }

    public MemTracker getTracker() {
        return memTracker;
    }

    public static TrackerSingle3 getInstance() {
        return INSTANCE;
    }
}

