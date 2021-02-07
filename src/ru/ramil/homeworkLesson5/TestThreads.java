package ru.ramil.homeworkLesson5;

public class TestThreads {

    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {
        computeInOneThread();
        computeInTwoThread();
    }

    private static void computeInOneThread() {
        float[] arr = new float[size];
        for(int i = 0; i < size; i++) {
            arr[i] = 1.0f;
        }
        long a = System.currentTimeMillis();
        for(int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5)
                    * Math.cos(0.2f + i / 5)
                    * Math.cos(0.4f + i / 2));
        }
        System.out.println(System.currentTimeMillis() - a);
    }

    private static void computeInTwoThread() {
        float[] arr = new float[size];
        for(int i = 0; i < size; i++) {
            arr[i] = 1.0f;
        }
        float[] arr1 = new float[h];
        float[] arr2 = new float[h];
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);
        Thread thread1 = new Thread(() -> {
            for(int i = 0; i < h; i++) {
                arr1[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5)
                        * Math.cos(0.2f + i / 5)
                        * Math.cos(0.4f + i / 2));
            }
        });
        Thread thread2 = new Thread(() -> {
            for(int i = 0; i < h; i++) {
                int n = i + h;
                arr2[i] = (float)(arr2[i] * Math.sin(0.2f + n / 5)
                        * Math.cos(0.2f + n / 5)
                        * Math.cos(0.4f + n / 2));
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        try {
            thread2.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);
        System.out.println(System.currentTimeMillis() - a);
    }
}
