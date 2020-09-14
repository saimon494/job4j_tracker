package ru.job4j.tracker;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ValidateInputTest {

    @Test
    public void whenValidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"one", "1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("--> Select: ");
        assertThat(selected, is(1));
    }

    @Test
    public void whenInvalidKeyInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"one", "1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("--> Select: ");
        assertThat(selected, is(
                String.format("Please enter validate data again.%n")
        ));
//        assertThat(out.toString(), is("Please enter validate data again."));
    }
}