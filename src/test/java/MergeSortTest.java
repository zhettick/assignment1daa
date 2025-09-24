import org.junit.jupiter.api.Test;
import org.sabulla.Metrics;
import org.sabulla.MergeSort;
import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    void testArray() {
        Metrics metrics = new Metrics();
        int[] arr = {82, 142, 78, 950, 286, 56, 12, 129, 82, 99, 12, 40, 7};
        MergeSort.sort(arr, metrics);
        assertTrue(isSorted(arr));
        assertTrue(metrics.getTime() > 0);
        assertTrue(metrics.getComparisons() > 0);
        assertTrue(metrics.getSwaps() > 0);
        assertTrue(metrics.getMaxDepth() > 0);
    }

    @Test
    void testSmallArray() {
        Metrics metrics = new Metrics();
        int[] arr = {78, 82, 142, 78};
        MergeSort.sort(arr, metrics);
        assertTrue(isSorted(arr));
        assertTrue(metrics.getTime() > 0);
        assertTrue(metrics.getComparisons() > 0);
        assertTrue(metrics.getSwaps() > 0);
        assertTrue(metrics.getMaxDepth() > 0);
    }

    @Test
    void testEmptyArray() {
        Metrics metrics = new Metrics();
        int[] arr = {};
        MergeSort.sort(arr, metrics);
        assertTrue(isSorted(arr));
        assertTrue(metrics.getTime() >= 0);
        assertEquals(0, metrics.getComparisons());
        assertEquals(0,metrics.getSwaps());
        assertEquals(0,metrics.getMaxDepth());
    }

    private boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }
}
