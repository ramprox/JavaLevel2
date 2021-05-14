package ru.ramil.homeworkLesson4;

import java.util.*;

public class TestLambda {
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
                .map(array -> {
                    for(int i = 0; i < array.length; i++) {
                        if(Optional.ofNullable(array[i])
                                .map(element -> element.equals(n))
                                .orElse(n == array[i])) {
                            return i;
                        }
                    }
                    return null;
                })
                .orElse(-1);
    }

    private static String reverse(String s) {
        return Optional.ofNullable(s)
                .map(string -> {
                    int[] codes = s.codePoints().toArray();
                    int[] reverseCodes = new int[codes.length];
                    for(int i = 0; i < codes.length; i++) {
                        reverseCodes[i] = codes[codes.length - 1 - i];
                    }
                    return new String(reverseCodes, 0, reverseCodes.length);
                })
                .orElse("");
    }

    private static Integer maximum(Integer[] list) {
        return Optional.ofNullable(list)
                .map(array -> {
                    Integer max = null;
                    for (Integer integer : array) {
                        Integer finalMax = max;
                        if(Optional.ofNullable(integer)
                                .map(element -> finalMax == null || finalMax < element)
                                .orElse(false)) {
                            max = integer;
                        }
                    }
                    return max;
                })
                .orElse(0);
    }

    private static Double average(List<Integer> list) {
        return Optional.ofNullable(list)
                .map(array -> {
                    double average = 0.0;
                    for (Integer integer : list) {
                        average += Optional.ofNullable(integer)
                                .map(element -> (double)element / list.size())
                                .orElse(0.0);
                    }
                    return average;
                }).orElse(0.0);
    }

    private static List<String> search(List<String> list) {
        return Optional.ofNullable(list)
                .map(listing -> {
                    List<String> result = new ArrayList<>();
                    for(String s : listing) {
                        if(Optional.ofNullable(s)
                                .map(element -> element.length() == 3 && element.charAt(0) == 'a')
                                .orElse(false)) {
                            result.add(s);
                        }
                    }
                    return result;
                })
                .orElse(new ArrayList<>());
    }
}
