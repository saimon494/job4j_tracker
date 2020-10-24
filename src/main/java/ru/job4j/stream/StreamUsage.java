package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamUsage {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(1, 2, -3, 4, -45, 13));
        List<Integer> positive = list.stream().filter(
                num -> num > 0
        ).collect(Collectors.toList());
        System.out.println(positive);
    }
}
