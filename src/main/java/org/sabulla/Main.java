package org.sabulla;

public class Main {
    public static void main(String[] args) {
        String name = args[0];
        int size = Integer.parseInt(args[1]);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
        Metrics metrics = new Metrics();

        switch (name) {
            case "mergesort":
                MergeSort.sort(arr, metrics);
                break;
            case "quicksort":
                QuickSort.sort(arr, metrics);
                break;
            case "select":
                int index = Integer.parseInt(args[2]);
                Select.select(arr, 0, arr.length - 1, index, metrics);
                break;
            case "closestpair":
                Point[] points = new Point[size];
                for (int i = 0; i < size; i++) {
                    points[i] = new Point((int) (Math.random() * 1000), (int) (Math.random() * 1000));
                }
                ClosestPair.closestPair(points, metrics);
                break;
        }
        String csvFile = "results.csv";
        CSVWriter.write(csvFile, name, size, metrics);
        System.out.println("Algorithm: " + name);
        System.out.println("Size: " + size);
        System.out.println("Time: " + metrics.getTime() + " ms");
        System.out.println("Comparisons: " + metrics.getComparisons());
        System.out.println("Swaps: " + metrics.getSwaps());
        System.out.println("Depth: " + metrics.getMaxDepth());
    }
}