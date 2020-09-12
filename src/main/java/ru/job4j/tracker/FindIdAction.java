package ru.job4j.tracker;

public class FindIdAction implements UserAction {
    private final Output out;

    public FindIdAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find item by id";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int id = input.askInt("--> Enter item id: ");
        Item newItem = tracker.findById(id);
        if (newItem != null) {
            out.println(newItem);
        } else {
            out.println("No such item with id " + id);
        }
        return true;
    }
}
