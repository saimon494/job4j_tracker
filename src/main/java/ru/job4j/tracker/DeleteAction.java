package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    @Override
    public String name() {
        return "Delete item";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int id = input.askInt("--> Enter item id: ");
        if (tracker.delete(id)) {
            System.out.println("Item with id " + id + " deleted");
        } else {
            System.out.println("No such id");
        }
        return true;
    }
}
