package ru.ramil.homeworkLesson8.clientside.service;

import ru.ramil.homeworkLesson8.clientside.model.ConnectionInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends JFrame {
    private static final Dimension MINIMUM_SIZE = new Dimension(400, 400);
    private final Integer SERVER_PORT = 8081;
    private final String SERVER_ADDRESS = "localhost";
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    ConnectionInfo connectionInfo = new ConnectionInfo();

    private JTextField msgInputField;
    private JTextArea chatArea;

    private JMenuItem menuItemConnect;
    private JMenuItem menuItemDisconnect;

    private static final String errSPM = "/errorSPM";   // error send private message
    private static final String clients = "/clients";   // получить список онлайн пользователей

    public Client() {
        prepareGUI();
        setConnected(false);
        tryConnection();
    }

    private void tryConnection() {
        try {
            connection();
        } catch (IOException e) {
            showErrorMessage("Сервер не отвечает");
        }
    }

    private void connection() throws IOException {
        setConnected(false);
        socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
        setConnected(true);
        new Thread(() -> {
            try {
                while (true) {
                    String messageFromServer = dis.readUTF();
                    if (messageFromServer.startsWith("/authok")) {
                        String[] arr = messageFromServer.split("\\s");
                        connectionInfo.setAuthorized(true);
                        showInfoMessage("Вы вошли в чат. Ваш ник " + arr[1]);
                        setTitle(arr[1]);
                        break;
                    }
                    showErrorMessage(messageFromServer);
                }
                while (true) {
                    String messageFromServer = dis.readUTF();
                    if(messageFromServer.startsWith(errSPM)) {
                        String errMsg = messageFromServer.substring(errSPM.length());
                        showErrorMessage(errMsg);
                        continue;
                    }
                    if(messageFromServer.startsWith(clients)) {
                        String[] arr = messageFromServer.split("\\s", 2);
                        messageFromServer = "[Список онлайн пользователей]: " + arr[1];
                    }
                    chatArea.append(messageFromServer + "\n");
                }
            } catch (IOException ignored) {
                showInfoMessage("Соединение разорвано");
            } finally {
                closeConnection();
                setTitle("Клиент");
            }
        }).start();
    }

    private void setConnected(boolean connected) {
        connectionInfo.setConnected(connected);
        if(connected) {
            menuItemConnect.setEnabled(false);
            menuItemDisconnect.setEnabled(true);
        } else {
            menuItemConnect.setEnabled(true);
            menuItemDisconnect.setEnabled(false);
        }
    }

    private void send() {
        String messageToServer = msgInputField.getText();
        if(!messageToServer.trim().isEmpty()) {
            if(connectionInfo.isConnected()) {
                sendMessageToServer(messageToServer);
                msgInputField.setText("");
                msgInputField.grabFocus();
            } else {
                showErrorMessage("Вы не в сети");
            }
        }
    }

    private void sendMessageToServer(String message) {
        try{
            dos.writeUTF(message);
            if(message.equals("/end")) {
                closeConnection();
                showInfoMessage("Соединение разорвано");
            }
        } catch (IOException ignored) {
            showInfoMessage("Соединение разорвано");
        }
    }

    private void closeConnection() {
        setConnected(false);
        if(dis != null) {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(dos != null) {
            try {
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showInfoMessage(String message) {
        JOptionPane.showMessageDialog(this, message,
                "Информация", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message,
                "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    private void prepareGUI() {
        setMinimumSize(MINIMUM_SIZE);
        setSize(MINIMUM_SIZE);
        setLocationRelativeTo(null);
        setTitle("Клиент");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton btnSendMsg = new JButton("Отправить");
        bottomPanel.add(btnSendMsg, BorderLayout.EAST);
        msgInputField = new JTextField();
        bottomPanel.add(msgInputField, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        btnSendMsg.addActionListener(e -> send());
        msgInputField.addActionListener(e -> send());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                closeConnection();
            }
        });

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menuServer = new JMenu("Сервер");
        menuBar.add(menuServer);
        menuItemConnect = new JMenuItem("Подключиться");
        menuItemConnect.addActionListener(e -> tryConnection());
        menuServer.add(menuItemConnect);

        menuItemDisconnect = new JMenuItem("Отключиться");
        menuItemDisconnect.addActionListener(e -> sendMessageToServer("/end"));
        menuServer.add(menuItemDisconnect);
        setVisible(true);
        msgInputField.grabFocus();
    }
}
