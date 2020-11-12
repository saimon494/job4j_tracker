package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class EasyStream {

    private final Consumer consumer;

    private static ArrayList<Integer> items = new ArrayList<>();

    public EasyStream(Consumer consumer) {
        this.consumer = consumer;
    }

    public static EasyStream of(List<Integer> source) {
        return new EasyStream(integer -> items.addAll(source));
    }

    public EasyStream map(Function<Integer, Integer> fun) {
        throw new UnsupportedOperationException();
    }

    public EasyStream filter(Predicate<Integer> fun) {
        return new EasyStream(value -> {
            if (fun.test((Integer) value)) {
                items.add((Integer) value);
            }
        });
    }

    public List<Integer> collect() {
        return items;
    }
}

