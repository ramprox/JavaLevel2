package ru.ramil.homeworkLesson1.Obstacles;

public abstract class Obstacle {
    private float size;
    protected Obstacle(float size) {
        this.size = size;
    }

    protected float getSize() {
        return size;
    }
}
