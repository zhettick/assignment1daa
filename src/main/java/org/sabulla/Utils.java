package org.sabulla;

public class Utils {
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

    public static void shuffle(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }
}