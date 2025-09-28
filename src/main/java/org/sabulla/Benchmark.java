package org.sabulla;

public class Benchmark {
    public static void runBenchmark() {
        int[] arr = generateRandomArray(10000, 0, 100000);
        int index = (int) (Math.random() * arr.length);

        int[] arr1 = arr.clone();
        Metrics selectMetrics = new Metrics();
        int selectResult = Select.runSelect(arr1, index, selectMetrics);
        System.out.println("Select             " + " | Time: " + selectMetrics.getTime() / 1000000.0 + " ms"
                + " | Comparisons: " + selectMetrics.getComparisons()
                + " | Swaps: " + selectMetrics.getSwaps()
                + " | Depth: " + selectMetrics.getMaxDepth()
                + " | Result: " + selectResult);

        int[] arr2 = arr.clone();
        Metrics mergeMetrics = new Metrics();
        MergeSort.sort(arr2, mergeMetrics);
        int mergeResult = arr2[index];
        System.out.println("Merge sort + select" + " | Time: " + mergeMetrics.getTime() / 1000000.0 + " ms"
                + " | Comparisons: " + mergeMetrics.getComparisons()
                + " | Swaps: " + mergeMetrics.getSwaps()
                + " | Depth: " + mergeMetrics.getMaxDepth()
                + " | Result: " + mergeResult);

        int[] arr3 = arr.clone();
        Metrics quickMetrics = new Metrics();
        QuickSort.sort(arr3, quickMetrics);
        int quickResult = arr2[index];
        System.out.println("Quick sort + select" + " | Time: " + quickMetrics.getTime() / 1000000.0 + " ms"
                + " | Comparisons: " + quickMetrics.getComparisons()
                + " | Swaps: " + quickMetrics.getSwaps()
                + " | Depth: " + quickMetrics.getMaxDepth()
                + " | Result: " + quickResult);
    }

    private static int[] generateRandomArray(int size, int min, int max) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * (max - min + 1)) + min;
        }
        return arr;
    }
}