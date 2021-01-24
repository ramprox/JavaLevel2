package ru.ramil.homeworkLesson1.Obstacles;

import static ru.ramil.homeworkLesson1.UniformRandom.*;

public class RunningTrack extends Obstacle {
    private static final float MIN_POSSIBLE_LENGTH = 40;
    private static final float MAX_POSSIBLE_LENGTH = 70;

    public RunningTrack(float length) {
        super(length);
    }
    public RunningTrack() {
        this(getRandomLength());
    }

    private static float getRandomLength() {
        return round(getValue(MIN_POSSIBLE_LENGTH, MAX_POSSIBLE_LENGTH), 1);
    }

    public float getLength() {
        return getSize();
    }

    @Override
    public String toString() {
        return "Беговая дорожка { " +
                "Длина: " + getLength() + "м }";
    }
}
