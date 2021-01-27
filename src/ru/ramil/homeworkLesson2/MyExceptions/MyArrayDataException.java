package ru.ramil.homeworkLesson2.MyExceptions;

public class MyArrayDataException extends Exception {
    private final int row;
    private final int col;

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public MyArrayDataException(String message, int row, int col) {
        super(message);
        this.row = row;
        this.col = col;
    }
}
