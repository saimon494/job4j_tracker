package ru.job4j.tracker;

public class ReplaceAction implements UserAction {
    private final Output out;

    public ReplaceAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Replace item";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int id = input.askInt("--> Enter item id to replace: ");
        String name = input.askStr("--> Enter item name to replace from: ");
        Item newItem = new Item(name);
        if (tracker.replace(id, newItem)) {
            System.out.println("Item with id " + id + " update");
        } else {
            System.out.println("No such id");
        }
        return true;
    }
}
