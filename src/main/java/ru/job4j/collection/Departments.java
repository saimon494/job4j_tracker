package ru.job4j.collection;

import java.util.*;

public class Departments {
    public static List<String> fillGaps(List<String> deps) {
        Set<String> tmp = new TreeSet<>();
        for (String value : deps) {
            String dept = "";
            for (String el : value.split("/")) {
                if (dept.equals("")) {
                    dept = el;
                } else {
                    dept = dept + "/" + el;
                }
                tmp.add(dept);
            }
        }
        return new ArrayList<>(tmp);
    }

    public static void sortAsc(List<String> orgs) {
        orgs.sort(Comparator.naturalOrder());
    }

    public static void sortDesc(List<String> orgs) {
        orgs.sort(new DepDescComp());
    }
}
