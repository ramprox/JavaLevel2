package ru.ramil.homeworkLesson1.Competitors;

import ru.ramil.homeworkLesson1.Obstacles.*;

public interface Competitor {

    String getName();
    float getMaxRunLength();
    float getMaxJumpHeight();

    boolean run(RunningTrack track);
    boolean jump(Wall wall);

    default boolean isOvercome(Obstacle obstacle) {
        if(obstacle instanceof RunningTrack) {
            return run((RunningTrack)obstacle);
        } else if(obstacle instanceof Wall) {
            return jump((Wall)obstacle);
        } else {
            System.out.println("Неизвестный тип препятствия");
            return false;
        }
    }

    default void tryOvercome(Obstacle[] obstacles) {
        for(Obstacle obstacle : obstacles) {
            if(!isOvercome(obstacle))
                return;
        }
    }
}
