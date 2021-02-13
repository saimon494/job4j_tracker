package ru.job4j.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class SqlTrackerTest {
    public Connection init() {
        try (InputStream in = SqlTracker.class.
                getClassLoader().
                getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("item"));
            assertThat(tracker.findByName("item").size(), is(1));
        }
    }

    @Test
    public void whenAdd() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item(0);
            tracker.add(item);
            assertTrue(item.getId() > 0);
        }
    }

    @Test
    public void whenReplace() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item(0, "item1");
            tracker.add(item);
            item.setName("item2");
            tracker.replace(item.getId(), item);
            assertThat(tracker.findById(item.getId()).getName(), is("item2"));
        }
    }

    @Test
    public void whenDelete() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item(0);
            tracker.add(item);
            assertTrue(tracker.delete(item.getId()));
        }
    }

    @Test
    public void whenFindAll() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item1 = new Item(0, "item1");
            Item item2 = new Item(1, "item2");
            tracker.add(item1);
            tracker.add(item2);
            List<Item> items = tracker.findAll();
            assertThat(items.contains(item2), is(true));
        }
    }

    @Test
    public void whenFindByName() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item1 = new Item(0, "item1");
            Item item2 = new Item(1, "item2");
            tracker.add(item1);
            tracker.add(item2);
            List<Item> items = tracker.findByName("item2");
            assertThat(items.size(), is(1));
        }
    }

    @Test
    public void whenFindById() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item(0, "item");
            tracker.add(item);
            Item rsl = tracker.findById(item.getId());
            assertThat(rsl.getName(), is(item.getName()));
        }
    }
}