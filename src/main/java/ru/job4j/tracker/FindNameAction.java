package ru.job4j.tracker;

public class FindNameAction implements UserAction {
    @Override
    public String name() {
        return "Find items by name";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String name = input.askStr("--> Enter items name: ");
        Item[] newItems = tracker.findByName(name);
        if (newItems.length != 0) {
            for (Item i : newItems) {
                System.out.println(i);
            }
        } else {
            System.out.println("No such items with name " + "\"" + name + "\"");
        }
        return true;
    }
}
