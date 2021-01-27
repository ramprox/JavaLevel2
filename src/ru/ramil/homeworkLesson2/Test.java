package ru.ramil.homeworkLesson2;

import ru.ramil.homeworkLesson2.MyExceptions.*;

import java.util.Arrays;
import java.util.Random;

public class Test {

    private final static Random rand = new Random();

    public static void main(String[] args) {
        int rows = rand.nextInt(10);
        String[][] values = new String[rows][];
        for (int i = 0; i < values.length; i++) {
            values[i] = new String[rand.nextInt(10)];
            for (int j = 0; j < values[i].length; j++) {
                if ((i + j) % 2 == 0) {
                    values[i][j] = "d";
                } else {
                    values[i][j] = Integer.toString(rand.nextInt(10));
                }
            }
        }
        printArray(values);

        int sum = 0;
        boolean isExceptionHandled = false;
        while(!isExceptionHandled) {
            try {
                sum = tryEvaluateSum(values);
                isExceptionHandled = true;
            } catch (MyArraySizeException e) {
                System.out.println(e.getMessage());
                if(values == null) {
                    isExceptionHandled = true;
                } else {
                    int rowCount = e.getRowLength();
                    int colCount = e.getColLength();
                    String[][] tmp = new String[rowCount][colCount];
                    for (String[] strings : tmp) {
                        Arrays.fill(strings, "0");
                    }
                    int maxRows = values.length;
                    if(maxRows > rowCount) {
                        maxRows = rowCount;
                    }
                    for(int i = 0; i < maxRows; i++) {
                        if(values[i] == null)
                            continue;
                        int maxCols = values[i].length;
                        if(maxCols > colCount)
                            maxCols = colCount;
                        for(int j = 0; j < maxCols; j++) {
                            tmp[i][j] = values[i][j];
                        }
                    }
                    values = tmp;
                }
            } catch (MyArrayDataException e) {
                int row = e.getRow();
                int col = e.getCol();
                System.out.println(e.getMessage() + ": " + values[row][col]);
                values[e.getRow()][e.getCol()] = "0";
            }
        }
        System.out.println();
        printArray(values);
        System.out.println("Сумма: " + sum);
    }

    private static int tryEvaluateSum(String[][] values) throws MyArraySizeException, MyArrayDataException {
        int result = 0;
        int n = 4;
        if(values == null || values.length != n) {
            throw new MyArraySizeException(n, n);
        }
        int i = 0;
        int j = 0;
        try {
            for(; i < n; i++) {
                if(values[i] == null || values[i].length != n) {
                    throw new MyArraySizeException(n, n);
                }
                j = 0;
                for(; j < n; j++) {
                    result += Integer.parseInt(values[i][j]);
                }
            }
        } catch (NumberFormatException e) {
            throw new MyArrayDataException("Нецелочисленные данные в ячейке {" + i + ", " + j + "}", i, j);
        }
        return result;
    }

    private static void printArray(String[][] values) {
        System.out.println("Содержимое массива:");
        if(values == null) {
            System.out.println("null");
            return;
        }
        for(String[] rows : values) {
            if(rows == null) {
                System.out.println("null");
                continue;
            }
            for(String value : rows) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
