package ru.ramil.homeworkLesson4;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestLambdaStream {
    public static void main(String[] args) {
        Integer[] ints = {2, 1, 5, null, 6, 4 };
        System.out.println("Массив Integer: " + Arrays.toString(ints));
        System.out.println("Индекс первого вхождения элемента 6: " + search(6, ints));
        String s = "java interview \uD83c\uDF7A";
        System.out.println("Исходная строка: " + s);
        System.out.println("Перевернутая строка: " + reverse(s));
        System.out.println("Максимум : " + maximum(ints));
        System.out.println("Среднее значение: " + average(Arrays.asList(ints)));
        List<String> strings = new ArrayList<>();
        strings.add("aaaa");
        strings.add(null);
        strings.add("abc");
        strings.add("any");
        strings.add("are");
        strings.add("qwerty");
        System.out.println("Массив строк: " + strings.toString());
        System.out.println("Слова с длиной 3 и которые начинаются на 'a': " + search(strings));
    }

    private static int search(Integer n, Integer[] list) {
        return Optional.ofNullable(list)
                .map(array -> IntStream.range(0, array.length)
                        .filter(i -> Optional.ofNullable(i)
                                .map(element -> element.equals(n))
                                .orElse(list[i] == n))
                        .findFirst()
                        .orElse(-1))
                .orElse(-1);
    }

    private static String reverse(String str) {
        return Optional.ofNullable(str)
                .map(s -> {
                    int[] codes = s.codePoints().toArray();
                    int[] ints = IntStream.range(0, codes.length)
                            .map(i -> codes[codes.length - 1 - i])
                            .toArray();
                    return new String(ints, 0, codes.length);
                })
                .orElse("");
    }

    private static Integer maximum(Integer[] list) {
        return Optional.ofNullable(list)
                .map(array -> Arrays.stream(list)
                        .max((x, y) -> Optional.ofNullable(x)
                                .map(element1 -> Optional.ofNullable(y)
                                        .map(element1::compareTo)
                                        .orElse(1))
                                .orElse(-1))
                        .orElse(0))
                .orElse(0);
    }

    private static Double average(List<Integer> list) {
        return Optional.ofNullable(list)
                .map(listing -> list.stream()
                        .mapToDouble(x -> Optional.ofNullable(x).orElse(0))
                        .average()
                        .orElse(0))
                .orElse(0.0);
    }

    private static List<String> search(List<String> list) {
        return Optional.ofNullable(list)
                .map(listing -> listing.stream()
                        .filter(str -> Optional.ofNullable(str)
                                .map(s -> (s.length() == 3) && (s.charAt(0) == 'a'))
                                .orElse(false))
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }
}
