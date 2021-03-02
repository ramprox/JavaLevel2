package ru.ramil.homeworkLesson8.clientside;

import ru.ramil.homeworkLesson8.clientside.service.Client;

import java.awt.*;

public class MainClientApp {
    public static void main(String[] args) {
        EventQueue.invokeLater(Client::new);
    }
}
