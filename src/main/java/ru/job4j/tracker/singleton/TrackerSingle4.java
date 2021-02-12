package ru.job4j.tracker.singleton;

import ru.job4j.tracker.MemTracker;

public class TrackerSingle4 {

    private final MemTracker memTracker = new MemTracker();

    private TrackerSingle4() {
    }

    public MemTracker getTracker() {
        return memTracker;
    }

    public static TrackerSingle4 getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final TrackerSingle4 INSTANCE = new TrackerSingle4();
    }
}
