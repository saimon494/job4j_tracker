package ru.job4j.tracker;

public class FindNameAction implements UserAction {
    private final Output out;

    public FindNameAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find items by name";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String name = input.askStr("--> Enter item name: ");
        Item[] newItems = tracker.findByName(name);
        if (newItems.length != 0) {
            for (Item i : newItems) {
                System.out.println(i);
            }
        } else {
            System.out.println("No such item with name " + "\"" + name + "\"");
        }
        return true;
    }
}
