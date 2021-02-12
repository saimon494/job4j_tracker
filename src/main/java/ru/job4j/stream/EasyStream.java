package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Function;
import java.util.function.Predicate;

public class EasyStream {

    private ListIterator<Integer> source;
    private ArrayList<Integer> rsl;

    public static class StreamBuilder {
        private EasyStream newEasyStream;

        public StreamBuilder() {
            newEasyStream = new EasyStream();
        }

        public StreamBuilder sourceList(List<Integer> source) {
            newEasyStream.source = source.listIterator();
            return this;
        }

        public StreamBuilder rslList() {
            newEasyStream.rsl = new ArrayList<>();
            return this;
        }

        public EasyStream build() {
            return newEasyStream;
        }
    }

    public static EasyStream of(List<Integer> source) {
        return new StreamBuilder()
                .sourceList(source)
                .rslList()
                .build();
    }

    public EasyStream map(Function<Integer, Integer> fun) {
        while (source.hasNext()) {
            Integer current = source.next();
            rsl.add(fun.apply(current));
        }
        return EasyStream.of(rsl);
    }

    public EasyStream filter(Predicate<Integer> fun) {
        while (source.hasNext()) {
            Integer current = source.next();
            if (fun.test(current)) {
                rsl.add(current);
            }
        }
        return EasyStream.of(rsl);
    }

    public List<Integer> collect() {
        while (source.hasNext()) {
            rsl.add(source.next());
        }
        return rsl;
    }
}

