package ru.ramil.homeworkLesson1.Competitors;

import ru.ramil.homeworkLesson1.Obstacles.*;
import static ru.ramil.homeworkLesson1.UniformRandom.*;

public class Human implements Competitor {

    private static final float MIN_POSSIBLE_OF_MAX_RUN_LENGTH = 30;
    private static final float MAX_POSSIBLE_OF_MAX_RUN_LENGTH = 150;
    private static final float MIN_POSSIBLE_OF_MAX_JUMP_HEIGHT = 0.5f;
    private static final float MAX_POSSIBLE_OF_MAX_JUMP_HEIGHT = 2.5f;

    private final float maxRunLength;
    private final float maxJumpHeight;
    private final String name;

    private final MessageBuilder message;

    public Human(String name, float maxRunLength, float maxJumpHeight) {
        this.name = name;
        this.maxRunLength = maxRunLength;
        this.maxJumpHeight = maxJumpHeight;
        message = new MessageBuilder();
    }

    public Human(String name) {
        this(name, getRandomMaxRunLength(), getRandomMaxJumpLength());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getMaxRunLength() {
        return maxRunLength;
    }

    @Override
    public float getMaxJumpHeight() {
        return maxJumpHeight;
    }

    private static float getRandomMaxRunLength() {
        return round(getValue(MIN_POSSIBLE_OF_MAX_RUN_LENGTH, MAX_POSSIBLE_OF_MAX_RUN_LENGTH), 1);
    }

    private static float getRandomMaxJumpLength() {
        return round(getValue(MIN_POSSIBLE_OF_MAX_JUMP_HEIGHT, MAX_POSSIBLE_OF_MAX_JUMP_HEIGHT), 1);
    }

    @Override
    public boolean run(RunningTrack track) {
        boolean result = false;
        float length = track.getLength();
        if(maxRunLength >= length) {
            message.insertSuccessfulResult(this, track);
            result = true;
        } else {
            message.insertUnsuccessfulResult(this, track);
        }
        System.out.println(message);
        return result;
    }

    @Override
    public boolean jump(Wall wall) {
        boolean result = false;
        float wallHeight = wall.getHeight();
        if(maxJumpHeight >= wallHeight) {
            message.insertSuccessfulResult(this, wall);
            result = true;
        } else {
            message.insertUnsuccessfulResult(this, wall);
        }
        System.out.println(message);
        return result;
    }

    @Override
    public String toString() {
        return "Человек\n" +
                "Имя: " + name + "\n" +
                "Максимальное расстояние, которое может пробежать: " + maxRunLength + "м\n" +
                "Максимальная высота, через которую может перепрыгнуть: " + maxJumpHeight + "м\n";
    }
}
