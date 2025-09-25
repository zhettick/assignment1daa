import org.junit.jupiter.api.Test;
import org.sabulla.Metrics;
import org.sabulla.QuickSort;
import org.sabulla.Utils;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {
    @Test
    void testArray() {
        Metrics metrics = new Metrics();
        int[] arr = {82, 142, 78, 950, 286, 56, 12, 129, 82, 99, 12, 40, 7};
        QuickSort.sort(arr, metrics);
        assertTrue(Utils.isSorted(arr));
        assertTrue(metrics.getTime() > 0);
        assertTrue(metrics.getComparisons() > 0);
        assertTrue(metrics.getSwaps() > 0);
        assertTrue(metrics.getMaxDepth() > 0);
    }

    @Test
    void testSmallArray() {
        Metrics metrics = new Metrics();
        int[] arr = {78, 82, 142, 78};
        QuickSort.sort(arr, metrics);
        assertTrue(Utils.isSorted(arr));
        assertTrue(metrics.getTime() > 0);
        assertTrue(metrics.getComparisons() > 0);
        assertTrue(metrics.getSwaps() > 0);
        assertTrue(metrics.getMaxDepth() > 0);
    }

    @Test
    void testEmptyArray() {
        Metrics metrics = new Metrics();
        int[] arr = {};
        QuickSort.sort(arr, metrics);
        assertTrue(Utils.isSorted(arr));
        assertTrue(metrics.getTime() >= 0);
        assertEquals(0, metrics.getComparisons());
        assertEquals(0,metrics.getSwaps());
        assertEquals(0,metrics.getMaxDepth());
    }
}