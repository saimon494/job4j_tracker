package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {
    private Connection cn;

    @Override
    public void init() {
        try (InputStream in = SqlTracker.class.
                getClassLoader().
                getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Item add(Item item) throws SQLException {
        try (PreparedStatement statement =
                cn.prepareStatement("insert into items(name) values (?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getInt(1));
                }
            }
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) throws SQLException {
        boolean result;
        try (PreparedStatement statement =
                     cn.prepareStatement("update items set name = ? where id = ?")) {
            statement.setString(1, item.getName());
            statement.setInt(2, id);
            result = statement.executeUpdate() > 0;
        }
        return result;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean result;
        try (PreparedStatement statement =
                     cn.prepareStatement("delete from items where id = ?")) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        }
        return result;
    }

    @Override
    public List<Item> findAll() throws SQLException {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement =
                     cn.prepareStatement("select * from items")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    items.add(new Item(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    ));
                }
            }
        }
        return items;
    }

    @Override
    public List<Item> findByName(String name) throws SQLException {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement =
                cn.prepareStatement("select * from items where name = ?")) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    items.add(new Item(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    ));
                }
            }
        }
        return items;
    }

    @Override
    public Item findById(int id) throws SQLException {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement =
                     cn.prepareStatement("select * from items where id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    items.add(new Item(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    ));
                }
            }
        }
        return items.size() > 0 ? items.get(0) : null;
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }
}