package org.sabulla;

public class QuickSort {
    public static void sort(int[] arr, Metrics metrics) {
        metrics.reset();
        metrics.start();
        quickSort(arr, 0, arr.length - 1, metrics);
        metrics.stop();
    }

    public static void quickSort(int[] arr, int start, int end, Metrics metrics) {
        if (start>=end) {
            return;
        }
        metrics.enter();
        int pi = Utils.partition(arr, start, end, metrics);
        int leftSide = pi - start;
        int rightSide = end - pi;
        if (leftSide < rightSide) {
            quickSort(arr, start, pi - 1, metrics);
            quickSort(arr, pi + 1, end, metrics);
        } else {
            quickSort(arr, pi + 1, end, metrics);
            quickSort(arr, start, pi - 1, metrics);
        }
        metrics.exit();
    }
}