package ru.job4j.tracker;

import org.junit.Test;

import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReplaceActionTest {

    @Test
    public void whenExecuteThenSuccess() throws SQLException {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        var replaceAction = new ReplaceAction(out);

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(replacedName);
        replaceAction.execute(input, tracker);
        String ln = System.lineSeparator();

        assertThat(out.toString(), is("Item with id 1 update" + ln));
        assertThat(tracker.findAll().get(0).getName(), is(replacedName));
    }

    @Test
    public void whenExecuteNotSuccess() throws SQLException {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        var replaceAction = new ReplaceAction(out);

        Input input = mock(Input.class);
        replaceAction.execute(input, tracker);
        String ln = System.lineSeparator();

        assertThat(out.toString(), is("No such id" + ln));
    }
}