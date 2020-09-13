package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {

    @Test
    public void whenCreateItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Input in = new StubInput(
                new String[]{"0", "Item name", "1"}
        );
        UserAction[] actions = {
                new CreateAction(out),
                new Exit()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                String.format(
                        "==== Menu ====%n"
                                + "0. Create a new item%n"
                                + "1. Exit Program%n"
                                + "New item created%n"
                                + "==== Menu ====%n"
                                + "0. Create a new item%n"
                                + "1. Exit Program%n"
                )
        ));
    }

    @Test
    public void whenShowItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("New item"));
        String newName = "New item";
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), newName, "1"}
        );
        UserAction[] actions = {
                new ShowAction(out),
                new Exit()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                String.format(
                        "==== Menu ====%n"
                                + "0. Show all items%n"
                                + "1. Exit Program%n"
                                + item.toString() + "%n"
                                + "==== Menu ====%n"
                                + "0. Show all items%n"
                                + "1. Exit Program%n"
                )
        ));
    }

    @Test
    public void whenNotShowItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Input in = new StubInput(
                new String[]{"0", "1"}
        );
        UserAction[] actions = {
                new ShowAction(out),
                new Exit()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                String.format(
                        "==== Menu ====%n"
                                + "0. Show all items%n"
                                + "1. Exit Program%n"
                                + "No items to show%n"
                                + "==== Menu ====%n"
                                + "0. Show all items%n"
                                + "1. Exit Program%n"
                )
        ));
    }

    @Test
    public void whenReplaceItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        UserAction[] actions = {
                new ReplaceAction(out),
                new Exit()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                String.format(
                        "==== Menu ====%n"
                                + "0. Replace item%n"
                                + "1. Exit Program%n"
                                + "Item with id " + item.getId()
                                + " update%n"
                                + "==== Menu ====%n"
                                + "0. Replace item%n"
                                + "1. Exit Program%n"
                )
        ));
    }

    @Test
    public void whenNotReplaceItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId() + 1), replacedName, "1"}
        );
        UserAction[] actions = {
                new ReplaceAction(out),
                new Exit()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                String.format(
                        "==== Menu ====%n"
                                + "0. Replace item%n"
                                + "1. Exit Program%n"
                                + "No such id%n"
                                + "==== Menu ====%n"
                                + "0. Replace item%n"
                                + "1. Exit Program%n"
                )
        ));
    }

    @Test
    public void whenDeleteItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), "1"}
        );
        UserAction[] actions = {
                new DeleteAction(out),
                new Exit()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                String.format(
                        "==== Menu ====%n"
                                + "0. Delete item%n"
                                + "1. Exit Program%n"
                                + "Item with id " + item.getId()
                                + " deleted%n"
                                + "==== Menu ====%n"
                                + "0. Delete item%n"
                                + "1. Exit Program%n"
                )
        ));
    }

    @Test
    public void whenNotDeleteItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId() + 1), "1"}
        );
        UserAction[] actions = {
                new DeleteAction(out),
                new Exit()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                String.format(
                        "==== Menu ====%n"
                                + "0. Delete item%n"
                                + "1. Exit Program%n"
                                + "No such id%n"
                                + "==== Menu ====%n"
                                + "0. Delete item%n"
                                + "1. Exit Program%n"
                )
        ));
    }

    @Test
    public void whenFindItemId() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Item"));
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), "1"}
        );
        UserAction[] actions = {
                new FindIdAction(out),
                new Exit()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                String.format(
                        "==== Menu ====%n"
                                + "0. Find item by id%n"
                                + "1. Exit Program%n"
                                + item.toString() + "%n"
                                + "==== Menu ====%n"
                                + "0. Find item by id%n"
                                + "1. Exit Program%n"
                )
        ));
    }

    @Test
    public void whenNotFindItemId() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Item"));
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId() + 1), "1"}
        );
        UserAction[] actions = {
                new FindIdAction(out),
                new Exit()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                String.format(
                        "==== Menu ====%n"
                                + "0. Find item by id%n"
                                + "1. Exit Program%n"
                                + "No such item with id "
                                + (item.getId() + 1) + "%n"
                                + "==== Menu ====%n"
                                + "0. Find item by id%n"
                                + "1. Exit Program%n"
                )
        ));
    }

    @Test
    public void whenFindItemName() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Item"));
        String name = "Item";
        Input in = new StubInput(
                new String[]{"0", name, "1"}
        );
        UserAction[] actions = {
                new FindNameAction(out),
                new Exit()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                String.format(
                        "==== Menu ====%n"
                                + "0. Find item by name%n"
                                + "1. Exit Program%n"
                                + item.toString() + "%n"
                                + "==== Menu ====%n"
                                + "0. Find item by name%n"
                                + "1. Exit Program%n"
                )
        ));
    }

    @Test
    public void whenNotFindItemName() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        String name = "Item";
        Input in = new StubInput(
                new String[]{"0", name, "1"}
        );
        UserAction[] actions = {
                new FindNameAction(out),
                new Exit()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                String.format(
                        "==== Menu ====%n"
                                + "0. Find item by name%n"
                                + "1. Exit Program%n"
                                + "No such item with name "
                                + "\"" + name + "\"" + "%n"
                                + "==== Menu ====%n"
                                + "0. Find item by name%n"
                                + "1. Exit Program%n"
                )
        ));
    }

    @Test
    public void whenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"1", "0"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new Exit()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                String.format(
                        "==== Menu ====%n"
                                + "0. Exit Program%n"
                                + "Wrong input, you can select: 0 .. 0%n"
                                + "==== Menu ====%n"
                                + "0. Exit Program%n"
                )
        ));
    }
}