package ru.ramil.homeworkLesson7.clientside;

import ru.ramil.homeworkLesson7.clientside.service.Client;

import java.awt.*;

public class MainClientApp {
    public static void main(String[] args) {
        EventQueue.invokeLater(Client::new);
    }
}
