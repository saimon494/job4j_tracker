package ru.job4j.tracker;

import java.sql.SQLException;

public class CreateAction implements UserAction {
    private final Output out;

    public CreateAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Create a new item";
    }

    @Override
    public boolean execute(Input input, Store tracker) throws SQLException {
        String name = input.askStr("--> Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
        out.println("New item created");
        return true;
    }
}