package org.sabulla;

import java.util.NoSuchElementException;

public class Select {
    public static int select(int[] arr, int start, int end, int index, Metrics metrics) {
        metrics.reset();
        metrics.start();
        int size = end - start + 1;
        if (size == 0) {
            throw new NoSuchElementException("Array is empty");
        } else if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Invalid index");
        } else if (size == 1) {
            return arr[start];
        }
        if (size < 6) {
            MergeSort.insertionSort(arr, start, end, metrics);
            return arr[start + index];
        }
        int groups = (size + 4) / 5;
        int[] medians = new int[groups];
        int groupIndex = 0;
        for (int i = start; i <= end; i += 5) {
            int groupStart = i;
            int groupEnd = Math.min(i + 4, end);
            MergeSort.insertionSort(arr, groupStart, groupEnd, metrics);
            int medianIndex = (groupStart + groupEnd) / 2;
            medians[groupIndex] = arr[medianIndex];
            groupIndex++;
        }
        int pivot = select(medians, 0, medians.length - 1, groups / 2, metrics);
        for (int i = start; i <= end; i++) {
            if (arr[i] == pivot) {
                Utils.swap(arr, i, end, metrics);
                break;
            }
        }
        int pivotIndex = Utils.partition(arr, start, end, metrics);
        int relativePivot = pivotIndex - start;
        metrics.enter();
        int result;
        if (index == relativePivot) {
            result = arr[pivotIndex];
        } else if (index < relativePivot) {
            result = select(arr, start, pivotIndex - 1, index, metrics);
        } else {
            result = select(arr, pivotIndex + 1, end, index - (relativePivot + 1), metrics);
        }
        metrics.exit();
        metrics.stop();
        return result;
    }
}
