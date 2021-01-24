package ru.ramil.homeworkLesson1.Competitors;

import ru.ramil.homeworkLesson1.Obstacles.*;

public class MessageBuilder {

    private final StringBuilder message = new StringBuilder();

    public void insertSuccessfulResult(Competitor competitor, Obstacle obstacle) {
        message.delete(0, message.length());
        if(competitor instanceof Human) {
            message.append("Человек ");
        } else if(competitor instanceof Cat) {
            message.append("Кошка ");
        } else if(competitor instanceof Robot) {
            message.append("Робот ");
        }
        message.append(competitor.getName());
        if(obstacle instanceof RunningTrack) {
            message.append(" успешно пробежал(а) ");
            message.append(((RunningTrack) obstacle).getLength());
        } else if(obstacle instanceof Wall) {
            message.append(" успешно перепрыгнул(а) через ");
            message.append(((Wall) obstacle).getHeight());
        }
        message.append("м!");
    }

    public void insertUnsuccessfulResult(Competitor competitor, Obstacle obstacle) {
        message.delete(0, message.length());
        if(competitor instanceof Human) {
            message.append("Человек ");
        } else if(competitor instanceof Cat) {
            message.append("Кошка ");
        } else if(competitor instanceof Robot) {
            message.append("Робот ");
        }
        message.append(competitor.getName());
        if(obstacle instanceof RunningTrack) {
            message.append(" не смог(ла) пробежать ");
            message.append(((RunningTrack) obstacle).getLength());
            message.append("м, т.к. максимальное расстояние, которое он(а) может пробежать ");
            message.append(competitor.getMaxRunLength());
        } else if(obstacle instanceof Wall) {
            message.append(" не смог(ла) перепрыгнуть через ");
            message.append(((Wall) obstacle).getHeight());
            message.append("м, т.к. максимальная высота, через которую он(а) может перепрыгнуть ");
            message.append(competitor.getMaxJumpHeight());
        }
        message.append("м!");
    }

    public String build() {
        return message.toString();
    }

    @Override
    public String toString() {
        return build();
    }
}
