package org.sabulla;

public class MergeSort {

    public static void sort(int[] arr, Metrics metrics) {
        metrics.reset();
        metrics.start();

        int[] buffer = new int[arr.length];
        mergeSort(arr, buffer, 0, arr.length - 1, metrics);

        metrics.stop();
    }

    private static void mergeSort(int[] arr, int[] buffer, int start, int end, Metrics metrics) {
        if (start >= end) {
            return;
        }

        int cutoff = 10;
        if (end - start + 1 <= cutoff) {
            insertionSort(arr, start, end, metrics);
        }

        metrics.enter();

        int mid = (start + end) / 2;
        mergeSort(arr, buffer, start, mid, metrics);
        mergeSort(arr, buffer, mid + 1, end, metrics);
        merge(arr, buffer, start, mid, end, metrics);

        metrics.exit();
    }

    private static void merge(int[] arr, int[] buffer, int start, int mid, int end, Metrics metrics) {
        int i = start, j = mid + 1, k = start;

        while (i <= mid && j <= end) {
            metrics.addComparison();
            if (arr[i] <= arr[j]) {
                buffer[k++] = arr[i++];
            } else {
                buffer[k++] = arr[j++];
                metrics.addSwap();
            }
        }

        while (i <= mid) {
            buffer[k++] = arr[i++];
        }

        while (j <= end) {
            buffer[k++] = arr[j++];
        }

        for (int t = start; t <= end; t++) {
            arr[t] = buffer[t];
        }
    }

    public static void insertionSort(int[] arr, int start, int end, Metrics metrics) {
        for (int i = start + 1; i <= end; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= start) {
                metrics.addComparison();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    metrics.addSwap();
                    j--;
                } else {
                    break;
                }
            }

            arr[j + 1] = key;
        }
    }
}