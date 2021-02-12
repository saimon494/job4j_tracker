package ru.job4j.tracker;

import java.sql.SQLException;
import java.util.List;

public class FindNameAction implements UserAction {
    private final Output out;

    public FindNameAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find item by name";
    }

    @Override
    public boolean execute(Input input, Store tracker) throws SQLException {
        String name = input.askStr("--> Enter item name: ");
        List<Item> newItems = tracker.findByName(name);
        if (newItems.size() != 0) {
            for (Item i : newItems) {
                out.println(i);
            }
        } else {
            out.println("No such item with name " + "\"" + name + "\"");
        }
        return true;
    }
}