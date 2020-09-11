package ru.job4j.tracker;

public class StartUI {

    public static void createItem(Input input, Tracker tracker) {
        System.out.println("=== Create a new Item ===");
        String name = input.askStr("--> Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
        System.out.println("New item created");
        System.out.println();
    }

    public static void showItems(Tracker tracker) {
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
    }

    public static void replaceItem(Input input, Tracker tracker) {
        System.out.println("=== Edit item ===");
        int id = input.askInt("--> Enter item id to replace: ");
        String name = input.askStr("--> Enter item name to replace from: ");
        Item newItem = new Item(name);
        if (tracker.replace(id, newItem)) {
            System.out.println("Item with id " + id + " update");
        } else {
            System.out.println("No such id");
        }
        System.out.println();
    }

    public static void deleteItem(Input input, Tracker tracker) {
        System.out.println("=== Delete item ===");
        int id = input.askInt("--> Enter item id: ");
        if (tracker.delete(id)) {
            System.out.println("Item with id " + id + " deleted");
        } else {
            System.out.println("No such id");
        }
        System.out.println();
    }

    public static void findItemById(Input input, Tracker tracker) {
        System.out.println("=== Find item by id ===");
        int id = input.askInt("--> Enter item id: ");
        Item newItem = tracker.findById(id);
        if (newItem != null) {
            System.out.println(newItem);
        } else {
            System.out.println("No such item with id " + id);
        }
        System.out.println();
    }
    public static void findItemByName(Input input, Tracker tracker) {
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
    }

    public static boolean exit() {
        System.out.println("=== Exit Program ===");
        return false;
    }

    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = input.askInt("--> Select: ");
            switch (select) {
                case 0 -> createItem(input, tracker);
                case 1 -> showItems(tracker);
                case 2 -> replaceItem(input, tracker);
                case 3 -> deleteItem(input, tracker);
                case 4 -> findItemById(input, tracker);
                case 5 -> findItemByName(input, tracker);
                case 6 -> run = exit();
            }
        }
    }

    private void showMenu() {
        System.out.println("==== Menu ====");
        System.out.println("0. Add new item");
        System.out.println("1. Show all items");
        System.out.println("2. Replace item");
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
