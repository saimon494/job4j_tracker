package ru.job4j.tracker;

public class StartUI {

    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = input.askInt("--> Select: ");
            if (select == 0) {
                System.out.println("=== Create a new Item ===");
                String name = input.askStr("--> Enter name: ");
                Item item = new Item(name);
                tracker.add(item);
                System.out.println("New item created");
                System.out.println();
            } else if (select == 1) {
                System.out.println("=== Show all items ===");
                Item[] items = tracker.findAll();
                if (items.length != 0) {
                    for (Item i : items) {
                        System.out.println(i);
                    }
                } else {
                    System.out.println("No items to show");
                }
                System.out.println();
            } else if (select == 2) {
                System.out.println("=== Edit item ===");
                int id = input.askInt("--> Enter item id to edit: ");
                String name = input.askStr("--> Enter item name to replace from: ");
                Item newItem = new Item(name);
                if (tracker.replace(id, newItem)) {
                    System.out.println("Item with id " + id + " update");
                } else {
                    System.out.println("No such id");
                }
                System.out.println();
            } else if (select == 3) {
                System.out.println("=== Delete item ===");
                int id = input.askInt("--> Enter item id: ");
                if (tracker.delete(id)) {
                    System.out.println("Item with id " + id + " deleted");
                } else {
                    System.out.println("No such id");
                }
                System.out.println();
            } else if (select == 4) {
                System.out.println("=== Find item by id ===");
                int id = input.askInt("--> Enter item id: ");
                Item newItem = tracker.findById(id);
                if (newItem != null) {
                    System.out.println(newItem);
                } else {
                    System.out.println("No such item with id " + id);
                }
                System.out.println();
            } else if (select == 5) {
                System.out.println("=== Find items by name ===");
                String name = input.askStr("--> Enter items name: ");
                Item[] newItems = tracker.findByName(name);
                if (newItems.length != 0) {
                    for (Item i : newItems) {
                        System.out.println(i);
                    }
                } else {
                    System.out.println("No such items with name " + "\"" + name + "\"");
                }
                System.out.println();
            } else if (select == 6) {
                System.out.println("=== Exit Program ===");
                run = false;
            }
        }
    }

    private void showMenu() {
        System.out.println("==== Menu ====");
        System.out.println("0. Add new item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);
    }
}
