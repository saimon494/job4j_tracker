package ru.job4j.tracker;

import java.sql.SQLException;
import java.util.List;

public class ShowAction implements UserAction {
    private final Output out;

    public ShowAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Show all items";
    }

    @Override
    public boolean execute(Input input, Store tracker) throws SQLException {
        List<Item> items = tracker.findAll();
        if (items.size() != 0) {
            for (Item i : items) {
                out.println(i);
            }
        } else {
            out.println("No items to show");
        }
        return true;
    }
}