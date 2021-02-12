package ru.job4j.lambda;

import java.util.Arrays;
import java.util.Comparator;

public class LambdaBlock {
    public static void main(String[] args) {
        Comparator<String> cmpText = (left, right) -> {
            System.out.println("compare strings: " + left + " and " + right);
            return left.compareTo(right);
        };
        Comparator<String> cmpDescSize = (left, right) -> {
            System.out.println("compare length of strings: "
                    + right.length() + " and " + left.length());
            return right.length() - left.length();
        };

        String[] arr = {"World", "Men", "Everybody"};
        System.out.println("default array: " + Arrays.toString(arr));
        Arrays.sort(arr, cmpText);
        System.out.println("lexico sort array: " + Arrays.toString(arr));
        Arrays.sort(arr, cmpDescSize);
        System.out.println("length sort array: " + Arrays.toString(arr));
    }
}
