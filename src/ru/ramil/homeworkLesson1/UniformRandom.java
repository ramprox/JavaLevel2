package ru.ramil.homeworkLesson1;

import java.util.Random;

public class UniformRandom {

    private static final Random rand = new Random();

    public static float getValue(float min, float max) {
        return rand.nextFloat() * (max - min) + min;
    }

    public static float round(float value, int digits) {
        int tenInExpDigits = (int)Math.pow(10, digits);
        value *= tenInExpDigits;
        value = (float)Math.round(value) / tenInExpDigits;
        return value;
    }
}
