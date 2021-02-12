package ru.job4j.tracker.singleton;

import ru.job4j.tracker.MemTracker;

public enum TrackerSingle1 {
    INSTANCE;

    private final MemTracker memTracker = new MemTracker();

    public MemTracker getTracker() {
        return memTracker;
    }
}

