package ru.ramil.homeworkLesson3;

import java.util.*;

public class TestUniqueWordsCount {

    private static Random rand = new Random();

    public static void main(String[] args) {
        String[] cities = {"Москва", "Берлин", "Париж", "Рим", "Лондон"};
        String[] words = new String[20];
        for(int i = 0; i < words.length; i++) {
            words[i] = cities[rand.nextInt(cities.length)];
        }
        printArray(words);
        System.out.println();

        Map<String, Integer> uniqueWords = new HashMap<>();
        for(String word : words) {
            Integer count = uniqueWords.get(word);
            uniqueWords.put(word, count == null ? 1 : count + 1);
        }
        printStringMap(uniqueWords);
    }

    private static void printStringMap(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Слово '" + entry.getKey() + "', количество повторений: " + entry.getValue());
        }
    }

    private static void printArray(String[] array) {
        for(int i = 0; i < array.length; i++) {
            if(i % 4 == 0) {
                System.out.println();
            }
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
