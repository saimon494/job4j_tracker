package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMap {
    public static List<Integer> collectMatrix(Integer[][] matrix) {
        return Arrays.stream(matrix)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList()
                );
    }
}
