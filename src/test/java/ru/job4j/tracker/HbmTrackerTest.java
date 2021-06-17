package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HbmTrackerTest {

    private HbmTracker tracker;

    @Before
    public void setUp() {
        tracker = new HbmTracker();
    }

    @Test
    public void wheAddAndFindAll() {
        tracker.add(new Item("Item1"));
        tracker.add(new Item("Item2"));
        List<Item> rsl = tracker.findAll();
        assertThat(rsl.size(), is(2));
        assertThat(rsl.get(0).getName(), is("Item1"));
        assertThat(rsl.get(1).getName(), is("Item2"));
    }

    @Test
    public void whenFindByName() {
        tracker.add(new Item("Item1"));
        tracker.add(new Item("Item2"));
        List<Item> rsl = tracker.findByName("Item2");
        assertThat(rsl.size(), is(1));
        assertThat(rsl.get(0).getName(), is("Item2"));
    }

    @Test
    public void whenFindById() {
        tracker.add(new Item("Item1"));
        Item rsl = tracker.findById(1);
        assertThat(rsl.getName(), is("Item1"));
    }

    @Test
    public void whenReplace() {
        tracker.add(new Item("Item1"));
        tracker.replace(1, new Item("Item2"));
        Item rsl = tracker.findById(1);
        assertThat(rsl.getName(), is("Item2"));
    }

    @Test
    public void whenDelete() {
        tracker.add(new Item("Item1"));
        tracker.delete(1);
        List<Item> rsl = tracker.findAll();
        assertThat(rsl.size(), is(0));
    }
}