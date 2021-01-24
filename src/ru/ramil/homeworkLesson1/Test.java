package ru.ramil.homeworkLesson1;

import ru.ramil.homeworkLesson1.Competitors.*;
import ru.ramil.homeworkLesson1.Obstacles.*;

public class Test {

    private static final int obstacleCount = 5;
    private static final String[] humanNames = {"Иван", "Петр", "Сергей", "Евгений"};
    private static final String[] catNames = {"Дымка", "Багира", "Мурка"};
    private static final String[] robotNames = {"Робокоп", "Терминатор", "R2-D2", "C-3PO", "Sonny"};

    public static void main(String[] args) {
        Obstacle[] obstacles = createObstacles();
        Competitor[] competitors = createCompetitors();
        System.out.println("Соревнования начинаются...");
        System.out.println();
        System.out.println("Полоса препятствий состоит из:");
        printArray(obstacles);
        System.out.println();
        System.out.println("Участники:");
        printArray(competitors);
        for(Competitor competitor : competitors) {
            competitor.tryOvercome(obstacles);
            System.out.println();
        }
    }

    private static Obstacle[] createObstacles() {
        Obstacle[] result = new Obstacle[obstacleCount];
        for(int i = 0; i < result.length - 1; i++) {
            if(i % 2 == 0) {
                result[i] = new RunningTrack();
            } else {
                result[i] = new Wall();
            }
        }
        result[result.length - 1] = new RunningTrack(50);
        return result;
    }

    private static Competitor[] createCompetitors() {
        int competitorsCount = humanNames.length + catNames.length + robotNames.length;
        Competitor[] competitors = new Competitor[competitorsCount];
        Human[] humans = createHumans();
        System.arraycopy(humans, 0, competitors, 0, humans.length);
        Cat[] cats = createCats();
        System.arraycopy(cats, 0, competitors, humans.length, cats.length);
        Robot[] robots = createRobots();
        System.arraycopy(robots, 0, competitors, humans.length + cats.length, robots.length);
        return competitors;
    }

    private static Human[] createHumans() {
        Human[] humans = new Human[humanNames.length];
        for(int i = 0; i < humans.length - 1; i++) {
            humans[i] = new Human(humanNames[i]);
        }
        humans[humans.length - 1] = new Human(humanNames[humanNames.length - 1], 150, 2.5f);
        return humans;
    }

    private static Cat[] createCats() {
        Cat[] cats = new Cat[catNames.length];
        for(int i = 0; i < cats.length - 1; i++) {
            cats[i] = new Cat(catNames[i]);
        }
        cats[cats.length - 1] = new Cat(catNames[catNames.length - 1], 100, 2.5f);
        return cats;
    }

    private static Robot[] createRobots() {
        Robot[] robots = new Robot[robotNames.length];
        for(int i = 0; i < robots.length - 1; i++) {
            robots[i] = new Robot(robotNames[i]);
        }
        robots[robots.length - 1] = new Robot(robotNames[robotNames.length - 1], 150, 2.5f);
        return robots;
    }

    private static void printArray(Object[] objs) {
        for(Object obj : objs) {
            System.out.println(obj);
        }
    }
}
