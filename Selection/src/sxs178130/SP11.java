package sxs178130;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class SP11 {


    public static Random random = new Random();

    public static <T extends Comparable<T>> Object[] kLargestHeap(T[] arr, int k) {


        Queue<T> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            pq.add(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {

            if (pq.peek().compareTo(arr[i]) < 0) {
                pq.poll();
                pq.add(arr[i]);
            }
        }
        return pq.toArray();

    }


    public static <T extends Comparable<T>> void kLargest(T[] arr, int k) {
        select(arr, 0, arr.length, k);
    }


    private static <T extends Comparable<T>> void select(T[] arr, int p, int n, int k) {



            int q = randomizedPartition(arr, p, p + n - 1);

            int left = q - p;
            int right = n - left - 1;
            if (right >= k)
                select(arr, q + 1, right, k);
            else if (right + 1 == k)
                return;
            else
                select(arr, p, left, k - right - 1);


    }
    static <T> void swap(T[] arr, int x, int y) {
        T tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }


    private static int getRandomNumberInRange(int min, int max) {

        if (min > max) {
            throw new IllegalArgumentException("max must be greater than min");
        }


        return random.nextInt((max - min) + 1) + min;
    }


    private static <T extends Comparable<T>> int randomizedPartition(T[] arr, int p, int r) {

        int i = getRandomNumberInRange(p, r);
        swap(arr, i, r);
        return partition(arr, p, r);
    }

    private static <T extends Comparable<T>> int partition(T[] arr, int p, int r) {
        T x = arr[r];
        int i = p - 1;

        for (int j = p; j < r; j++) {
            if (arr[j].compareTo(x) <= 0) {
                i = i + 1;
                swap(arr, i, j);

            }
        }

        swap(arr, i + 1, r);

        return (i + 1);
    }



    public static<T extends Comparable<T>> void insertionSort(T[] arr) {

        insertionSort(arr, 0, arr.length-1);

    }

    public static<T extends  Comparable<T>> void insertionSort(T[] arr, int p, int r) {
        for (int i = p+1; i <=r ; i++) {
            T temp = arr[i];
            int j  = i-1;
            while(j>=p && temp.compareTo(arr[j])<0){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = temp;
        }
    }


    public static void main(String[] args) throws Exception {

        int n = 8000000;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }


        int k = n/2;


        Shuffle.shuffle(arr);
        Timer timer1 = new Timer();


        kLargestHeap(arr, k);




        timer1.end();

        System.out.println("Using Binary Heap: " + "\n" + timer1);




        timer1 = new Timer();

        kLargest(arr, k);


        timer1.end();

        System.out.println("Using Selection: " + "\n" + timer1);




    }


    public static <T> void printArray(T[] arr, String message) {
        printArray(arr, 0, arr.length - 1, message);
    }

    public static <T> void printArray(T[] arr, int from, int to, String message) {
        System.out.print(message);
        for (int i = from; i <= to; i++) {
            System.out.print(" " + arr[i]);
        }
        System.out.println();
    }

    public static class Timer {
        long startTime, endTime, elapsedTime, memAvailable, memUsed;
        boolean ready;

        public Timer() {
            startTime = System.currentTimeMillis();
            ready = false;
        }

        public void start() {
            startTime = System.currentTimeMillis();
            ready = false;
        }

        public Timer end() {
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            memAvailable = Runtime.getRuntime().totalMemory();
            memUsed = memAvailable - Runtime.getRuntime().freeMemory();
            ready = true;
            return this;
        }

        public long duration() {
            if (!ready) {
                end();
            }
            return elapsedTime;
        }

        public long memory() {
            if (!ready) {
                end();
            }
            return memUsed;
        }

        public void scale(int num) {
            elapsedTime /= num;
        }

        public String toString() {
            if (!ready) {
                end();
            }
            return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed / 1048576) + " MB / " + (memAvailable / 1048576) + " MB.";
        }
    }


    public static class Shuffle {

        public static void shuffle(int[] arr) {
            shuffle(arr, 0, arr.length - 1);
        }

        public static <T> void shuffle(T[] arr) {
            shuffle(arr, 0, arr.length - 1);
        }

        public static void shuffle(int[] arr, int from, int to) {
            int n = to - from + 1;
            for (int i = 1; i < n; i++) {
                int j = random.nextInt(i);
                swap(arr, i + from, j + from);
            }
        }

        public static <T> void shuffle(T[] arr, int from, int to) {
            int n = to - from + 1;
            Random random = new Random();
            for (int i = 1; i < n; i++) {
                int j = random.nextInt(i);
                swap(arr, i + from, j + from);
            }
        }

        static void swap(int[] arr, int x, int y) {
            int tmp = arr[x];
            arr[x] = arr[y];
            arr[y] = tmp;
        }

        static <T> void swap(T[] arr, int x, int y) {
            T tmp = arr[x];
            arr[x] = arr[y];
            arr[y] = tmp;
        }
    }

}
