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
        int pi = partition(arr, start, end, metrics);
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

    public static int partition(int[] arr, int start, int end, Metrics metrics) {
        int pivot = start + (int) (Math.random() * (end - start + 1));
        swap(arr, pivot, end, metrics);
        pivot = arr[end];
        int current = start;
        for (int i = start; i < end; i++) {
            metrics.addComparison();
            if (arr[i] <= pivot) {
                swap(arr, current, i, metrics);
                current++;
            }
        }
        swap(arr, current, end, metrics);
        return current;
    }

    public static void swap(int[] arr, int indexOne, int indexTwo, Metrics metrics) {
        int temp = arr[indexTwo];
        arr[indexTwo] = arr[indexOne];
        arr[indexOne] = temp;
        metrics.addSwap();
    }
}
