package ru.ramil.homeworkLesson2.MyExceptions;

public class MyArraySizeException extends Exception {

    private final int rowLength;
    private final int colLength;

    public MyArraySizeException(String message, int rowLength, int colLength) {
        super(message);
        this.rowLength = rowLength;
        this.colLength = colLength;
    }

    public MyArraySizeException(int rowLength, int colLength) {
        this("Размер массива должен быть " + rowLength + "x" + colLength, rowLength, colLength);
    }

    public int getRowLength() {
        return rowLength;
    }

    public int getColLength() {
        return colLength;
    }
}
