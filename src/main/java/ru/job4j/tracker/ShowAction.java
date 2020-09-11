package ru.job4j.tracker;

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
    public boolean execute(Input input, Tracker tracker) {
        Item[] items = tracker.findAll();
        if (items.length != 0) {
            for (Item i : items) {
                System.out.println(i);
            }
        } else {
            System.out.println("No items to show");
        }
        return true;
    }
}
