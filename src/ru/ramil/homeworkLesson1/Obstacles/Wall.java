package ru.ramil.homeworkLesson1.Obstacles;

import static ru.ramil.homeworkLesson1.UniformRandom.*;

public class Wall extends Obstacle {

    private static final float MIN_POSSIBLE_HEIGHT = 1.5f;
    private static final float MAX_POSSIBLE_HEIGHT = 2.5f;

    public Wall() {
        this(getRandomHeight());
    }

    public Wall(float height) {
        super(height);
    }

    public float getHeight() {
        return getSize();
    }

    private static float getRandomHeight() {
        return round(getValue(MIN_POSSIBLE_HEIGHT, MAX_POSSIBLE_HEIGHT), 1);
    }

    @Override
    public String toString() {
        return "Стена { " +
                "Высота: " + getHeight() + "м }";
    }
}
