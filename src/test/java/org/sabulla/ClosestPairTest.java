package org.sabulla;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClosestPairTest {
    @Test
    void testInvalidArgumentThrows() {
        Metrics metrics = new Metrics();
        Point[] points = {new Point(0, 0)};
        assertThrows(IllegalArgumentException.class, () -> ClosestPair.closestPair(points, metrics));
        assertTrue(metrics.getTime() >= 0);
        assertEquals(0, metrics.getComparisons());
        assertEquals(0, metrics.getMaxDepth());
    }

    @Test
    void testThreePoints() {
        Metrics metrics = new Metrics();
        Point[] points = {
                new Point(0, 0),
                new Point(0, 3),
                new Point(4, 0)
        };
        double result = ClosestPair.closestPair(points, metrics);
        assertEquals(3.0, result, 1e-6);
        assertTrue(metrics.getTime() >= 0);
        assertTrue(metrics.getComparisons() > 0);
        assertTrue(metrics.getMaxDepth() <= (int) (Math.log(points.length) / Math.log(2)) + 5);
    }

    @Test
    void testFivePoints() {
        Metrics metrics = new Metrics();
        Point[] points = {
                new Point(0, 0),
                new Point(10, 10),
                new Point(2, 1),
                new Point(2, 2),
                new Point(100, 100)
        };
        double result = ClosestPair.closestPair(points, metrics);
        assertEquals(1.0, result, 1e-6);
        assertTrue(metrics.getTime() >= 0);
        assertTrue(metrics.getComparisons() > 0);
        assertTrue(metrics.getMaxDepth() <= (int) (Math.log(points.length) / Math.log(2)) + 5);
    }

    @Test
    void testDuplicatePoints() {
        Metrics metrics = new Metrics();
        Point[] points = {new Point(0, 0), new Point(0, 0), new Point(5, 5)};
        double dist = ClosestPair.closestPair(points, metrics);
        assertEquals(0.0, dist, 1e-6);
        assertTrue(metrics.getTime() >= 0);
        assertTrue(metrics.getComparisons() > 0);
        assertTrue(metrics.getMaxDepth() <= (int) (Math.log(points.length) / Math.log(2)) + 5);
    }
}