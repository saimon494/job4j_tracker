package ru.job4j.tracker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface Store extends AutoCloseable {
    void init() throws IOException;

    Item add(Item item) throws SQLException;

    boolean replace(int id, Item item) throws SQLException;

    boolean delete(int id) throws SQLException;

    List<Item> findAll() throws SQLException;

    List<Item> findByName(String name) throws SQLException;

    Item findById(int id) throws SQLException;
}