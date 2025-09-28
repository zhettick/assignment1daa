package org.sabulla;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SelectTest {
    @Test
    void testEmptyArrayThrows() {
        Metrics metrics = new Metrics();
        int[] arr = {};
        assertThrows(NoSuchElementException.class, () ->
                Select.runSelect(arr, 0, metrics)
        );
        assertTrue(metrics.getTime() >= 0);
        assertEquals(0, metrics.getComparisons());
        assertEquals(0, metrics.getSwaps());
        assertEquals(0, metrics.getMaxDepth());
    }

    @Test
    void testInvalidIndexThrows() {
        Metrics metrics = new Metrics();
        int[] arr = {3, 1, 2};
        assertThrows(IndexOutOfBoundsException.class, () ->
                Select.runSelect(arr,  5, metrics)
        );
        assertTrue(metrics.getTime() >= 0);
        assertEquals(0, metrics.getComparisons());
        assertEquals(0, metrics.getSwaps());
        assertEquals(0, metrics.getMaxDepth());
    }

    @Test
    void testSingleElement() {
        Metrics metrics = new Metrics();
        int[] arr = {42};
        int result = Select.runSelect(arr,  0, metrics);
        assertEquals(42, result);
        assertTrue(metrics.getTime() >= 0);
        assertEquals(0, metrics.getComparisons());
        assertTrue(metrics.getSwaps() >= 0);
        assertTrue(metrics.getMaxDepth() <= (int) (Math.log(arr.length) / Math.log(2)) + 10);
    }

    @Test
    void testSmallArray() {
        Metrics metrics = new Metrics();
        int[] arr = {78, 82, 142, 78};
        int[] copy = arr.clone();
        int result = Select.runSelect(copy, 2, metrics);
        Arrays.sort(arr);
        assertEquals(arr[2], result);
        assertTrue(metrics.getTime() >= 0);
        assertTrue(metrics.getComparisons() > 0);
        assertTrue(metrics.getSwaps() >= 0);
        assertTrue(metrics.getMaxDepth() <= (int) (Math.log(arr.length) / Math.log(2)) + 10);
    }

    @Test
    void testArray() {
        Metrics metrics = new Metrics();
        int[] arr = {82, 142, 78, 950, 286, 56, 12, 129, 82, 99, 12, 40, 7};
        int[] copy = arr.clone();
        int result = Select.runSelect(copy, 9, metrics);
        Arrays.sort(arr);
        assertEquals(arr[9], result);
        assertTrue(metrics.getTime() > 0);
        assertTrue(metrics.getComparisons() > 0);
        assertTrue(metrics.getSwaps() >= 0);
        assertTrue(metrics.getMaxDepth() <= (int) (Math.log(arr.length) / Math.log(2)) + 5);
    }

    @Test
    void testAllDuplicates() {
        Metrics metrics = new Metrics();
        int[] arr = {7, 7, 7, 7};
        int result = Select.runSelect(arr, 2, metrics);
        assertEquals(7, result);
        assertTrue(metrics.getTime() > 0);
        assertTrue(metrics.getComparisons() > 0);
        assertTrue(metrics.getSwaps() >= 0);
        assertTrue(metrics.getMaxDepth() <= (int) (Math.log(arr.length) / Math.log(2)) + 5);
    }
}