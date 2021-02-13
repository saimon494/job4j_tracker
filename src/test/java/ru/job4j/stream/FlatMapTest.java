package ru.job4j.stream;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FlatMapTest {

    @Test
    public void collectMatrix() {
        Integer[][] matrix = new Integer[][]{{1, 2}, {3, 4}, {5, 6}};
        List<Integer> rsl = FlatMap.collectMatrix(matrix);
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6);
        assertThat(rsl, is(expected));
    }
}