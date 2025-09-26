import org.junit.jupiter.api.Test;
import org.sabulla.Metrics;
import org.sabulla.Select;

import java.util.NoSuchElementException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SelectTest {
    @Test
    void testEmptyArrayThrows() {
        Metrics metrics = new Metrics();
        int[] arr = {};
        assertThrows(NoSuchElementException.class, () ->
                Select.select(arr, 0, arr.length - 1, 0, metrics)
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
                Select.select(arr, 0, arr.length - 1, 5, metrics)
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
        int result = Select.select(arr, 0, arr.length - 1, 0, metrics);
        assertEquals(42, result);
        assertTrue(metrics.getTime() >= 0);
        assertEquals(0, metrics.getComparisons());
        assertTrue(metrics.getSwaps() >= 0);
        assertTrue(metrics.getMaxDepth() <= arr.length);
    }

    @Test
    void testSmallArray() {
        Metrics metrics = new Metrics();
        int[] arr = {78, 82, 142, 78};
        int[] copy = arr.clone();
        int result = Select.select(copy, 0, arr.length - 1, 2, metrics);
        Arrays.sort(arr);
        assertEquals(arr[2], result);
        assertTrue(metrics.getTime() >= 0);
        assertTrue(metrics.getComparisons() > 0);
        assertTrue(metrics.getSwaps() >= 0);
        assertTrue(metrics.getMaxDepth() <= arr.length);
    }

    @Test
    void testArray() {
        Metrics metrics = new Metrics();
        int[] arr = {82, 142, 78, 950, 286, 56, 12, 129, 82, 99, 12, 40, 7};
        int[] copy = arr.clone();
        int result = Select.select(copy, 0, arr.length - 1, 9, metrics);
        Arrays.sort(arr);
        assertEquals(arr[9], result);
        assertTrue(metrics.getTime() > 0);
        assertTrue(metrics.getComparisons() > 0);
        assertTrue(metrics.getSwaps() >= 0);
        assertTrue(metrics.getMaxDepth() <= arr.length);
    }
}