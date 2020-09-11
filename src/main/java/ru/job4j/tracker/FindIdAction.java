package ru.job4j.tracker;

public class FindIdAction implements UserAction {
    @Override
    public String name() {
        return "Find item by id";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int id = input.askInt("--> Enter item id: ");
        Item newItem = tracker.findById(id);
        if (newItem != null) {
            System.out.println(newItem);
        } else {
            System.out.println("No such item with id " + id);
        }
        return true;
    }
}
