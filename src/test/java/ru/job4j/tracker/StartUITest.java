package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StartUITest {
    private Store tracker;
    private Store memTracker;

    @Before
    public void init() throws IOException {
        tracker = new SqlTracker();
        memTracker = new MemTracker();
        tracker.init();
    }

    @Test
    public void whenCreateItem() throws SQLException {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0", "Item name", "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new CreateAction(out),
                new Exit()
        );
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
    public void whenShowItem() throws SQLException {
        Output out = new StubOutput();
        Item item = memTracker.add(new Item("New item"));
        String newName = "New item";
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), newName, "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new ShowAction(out),
                new Exit()
        );
        new StartUI(out).init(in, memTracker, actions);
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
    public void whenNotShowItem() throws SQLException {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0", "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new ShowAction(out),
                new Exit()
        );
        new StartUI(out).init(in, memTracker, actions);
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
    public void whenReplaceItem() throws SQLException {
        Output out = new StubOutput();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new ReplaceAction(out),
                new Exit()
        );
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
    public void whenNotReplaceItem() throws SQLException {
        Output out = new StubOutput();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId() + 1), replacedName, "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new ReplaceAction(out),
                new Exit()
        );
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
    public void whenDeleteItem() throws SQLException {
        Output out = new StubOutput();
        Item item = tracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new DeleteAction(out),
                new Exit()
        );
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
    public void whenNotDeleteItem() throws SQLException {
        Output out = new StubOutput();
        Item item = tracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId() + 1), "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new DeleteAction(out),
                new Exit()
        );
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
    public void whenFindItemId() throws SQLException {
        Output out = new StubOutput();
        Item item = tracker.add(new Item("Item"));
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new FindIdAction(out),
                new Exit()
        );
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
    public void whenNotFindItemId() throws SQLException {
        Output out = new StubOutput();
        Item item = tracker.add(new Item("Item"));
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId() + 1), "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new FindIdAction(out),
                new Exit()
        );
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
    public void whenFindItemName() throws SQLException {
        Output out = new StubOutput();
        Item item = memTracker.add(new Item("Item"));
        String name = "Item";
        Input in = new StubInput(
                new String[]{"0", name, "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new FindNameAction(out),
                new Exit()
        );
        new StartUI(out).init(in, memTracker, actions);
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
    public void whenNotFindItemName() throws SQLException {
        Output out = new StubOutput();
        String name = "Item";
        Input in = new StubInput(
                new String[]{"0", name, "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new FindNameAction(out),
                new Exit()
        );
        new StartUI(out).init(in, memTracker, actions);
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
    public void whenExit() throws SQLException {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"1", "0"}
        );
        List<UserAction> actions = new ArrayList<>();
        actions.add(new Exit());
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