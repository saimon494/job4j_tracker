package ru.job4j.tracker.singleton;

import org.junit.Test;

import static org.junit.Assert.*;

public class MemTrackerSingleTest {

    @Test
    public void whenTrackerSingle1Equals() {
        TrackerSingle1 tracker1 =  TrackerSingle1.INSTANCE;
        TrackerSingle1 tracker2 =  TrackerSingle1.INSTANCE;
        assertEquals(tracker1, tracker2);
    }

    @Test
    public void whenTrackerSingle2Equals() {
        TrackerSingle2 tracker1 =  TrackerSingle2.getInstance();
        TrackerSingle2 tracker2 =  TrackerSingle2.getInstance();
        assertEquals(tracker1, tracker2);
    }

    @Test
    public void whenTrackerSingle3Equals() {
        TrackerSingle3 tracker1 =  TrackerSingle3.getInstance();
        TrackerSingle3 tracker2 =  TrackerSingle3.getInstance();
        assertEquals(tracker1, tracker2);
    }

    @Test
    public void whenTrackerSingle4Equals() {
        TrackerSingle4 tracker1 =  TrackerSingle4.getInstance();
        TrackerSingle4 tracker2 =  TrackerSingle4.getInstance();
        assertEquals(tracker1, tracker2);
    }
}