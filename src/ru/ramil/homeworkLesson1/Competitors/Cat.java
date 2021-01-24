package ru.ramil.homeworkLesson1.Competitors;

import ru.ramil.homeworkLesson1.Obstacles.*;
import static ru.ramil.homeworkLesson1.UniformRandom.*;

public class Cat implements Competitor {

    private static final float MIN_POSSIBLE_OF_MAX_RUN_LENGTH = 50;
    private static final float MAX_POSSIBLE_OF_MAX_RUN_LENGTH = 100;
    private static final float MIN_POSSIBLE_OF_MAX_JUMP_HEIGHT = 0.5f;
    private static final float MAX_POSSIBLE_OF_MAX_JUMP_HEIGHT = 2.0f;

    private final float maxRunLength;
    private final float maxJumpHeight;
    private final String name;
    private final StringBuilder message;

    public Cat(String name, float maxRunLength, float maxJumpHeight) {
        this.name = name;
        this.maxRunLength = maxRunLength;
        this.maxJumpHeight = maxJumpHeight;
        message = new StringBuilder();
    }

    public Cat(String name) {
        this(name, getRandomMaxRunLength(), getRandomMaxJumpLength());
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
            insertSuccessfulResultInMessage(track);
            result = true;
        } else {
            insertUnsuccessfulResultInMessage(track);
        }
        System.out.println(message);
        return result;
    }

    @Override
    public boolean jump(Wall wall) {
        boolean result = false;
        float wallHeight = wall.getHeight();
        if(maxJumpHeight >= wallHeight) {
            insertSuccessfulResultInMessage(wall);
            result = true;
        } else {
            insertUnsuccessfulResultInMessage(wall);
        }
        System.out.println(message);
        return result;
    }

    private void insertSuccessfulResultInMessage(Obstacle obstacle) {
        message.delete(0, message.length());
        message.append("Кошка по имени ");
        message.append(name);
        if(obstacle instanceof RunningTrack) {
            message.append(" успешно пробежала ");
            message.append(((RunningTrack)obstacle).getLength());
            message.append("м.");
        } else if(obstacle instanceof Wall) {
            message.append(" успешно перепрыгнула через ");
            message.append(((Wall)obstacle).getHeight());
            message.append("м.");
        }
    }

    private void insertUnsuccessfulResultInMessage(Obstacle obstacle) {
        message.delete(0, message.length());
        message.append("Кошка по имени ");
        message.append(name);
        if(obstacle instanceof RunningTrack) {
            message.append(" не смогла пробежать ");
            message.append(((RunningTrack)obstacle).getLength());
            message.append("м, т.к. максимальное расстояние, которое она может пробежать ");
            message.append(maxRunLength);
            message.append("м!");
        } else if(obstacle instanceof Wall) {
            message.append(" не смогла перепрыгнуть через стену высотой ");
            message.append(((Wall)obstacle).getHeight());
            message.append("м, т.к. максимальная высота, через которую она может перепрыгнуть ");
            message.append(maxJumpHeight);
            message.append("м!");
        }
    }

    @Override
    public String toString() {
        return "Кошка\n" +
                "Имя: " + name + "\n" +
                "Максимальное расстояние, которое может пробежать: " + maxRunLength + "\n" +
                "Максимальная высота, через которую может перепрыгнуть: " + maxJumpHeight + "\n";
    }
}