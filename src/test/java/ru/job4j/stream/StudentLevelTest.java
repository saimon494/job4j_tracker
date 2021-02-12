package ru.job4j.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StudentLevelTest {
    @Test
    public void whenSorted() {
        List<Student> input = new ArrayList<>();
        input.add(new Student(28, "Masha"));
        input.add(new Student(128, "Petya"));

        List<Student> expected = List.of(
                new Student(128, "Petya"),
                new Student(28, "Masha")
        );
        assertThat(StudentLevel.leveOf(input, 20), is(expected));
    }

    @Test
    public void whenOnlyNull() {
        List<Student> input = new ArrayList<>();
        input.add(null);
        List<Student> expected = List.of();
        assertThat(StudentLevel.leveOf(input, 100), is(expected));
    }

    @Test
    public void whenHasNull() {
        List<Student> input = new ArrayList<>();
        input.add(null);
        input.add(new Student(28, "Petya"));
        List<Student> expected = List.of(new Student(28, "Petya"));
        assertThat(StudentLevel.leveOf(input, 10), is(expected));
    }
}