package org.sabulla;

public class Metrics {
    private long comparisons;
    private long swaps;
    private long startTime;
    private long time;
    private int currentDepth;
    private int maxDepth;

    public void addComparison() {
        comparisons++;
    }

    public void addSwap() {
        swaps++;
    }

    public void enter() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public void exit() {
        currentDepth--;
    }

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        time = System.nanoTime() - startTime;
    }

    public void reset() {
        comparisons = 0;
        swaps = 0;
        time = 0;
        startTime = 0;
        currentDepth = 0;
        maxDepth = 0;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getSwaps() {
        return swaps;
    }

    public long getTime() {
        return time;
    }

    public int getCurrentDepth() {
        return currentDepth;
    }

    public int getMaxDepth() {
        return maxDepth;
    }
}