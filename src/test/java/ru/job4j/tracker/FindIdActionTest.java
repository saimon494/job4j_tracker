package ru.job4j.tracker;

import org.junit.Test;

import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FindIdActionTest {

    @Test
    public void whenExecuteThenSuccess() throws SQLException {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        Item item = tracker.add(new Item("New item"));
        var findIdAction = new FindIdAction(out);

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        findIdAction.execute(input, tracker);
        String ln = System.lineSeparator();

        assertThat(out.toString(), is(item.toString() + ln));
    }

    @Test
    public void whenExecuteNotSuccess() throws SQLException {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("New item"));
        var findIdAction = new FindIdAction(out);

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(2);
        findIdAction.execute(input, tracker);
        String ln = System.lineSeparator();

        assertThat(out.toString(), is("No such item with id 2" + ln));
    }
}