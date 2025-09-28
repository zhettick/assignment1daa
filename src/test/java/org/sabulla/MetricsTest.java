package org.sabulla;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetricsTest {
    @Test
    void testAddComparison() {
        Metrics metrics = new Metrics();
        assertEquals(0, metrics.getComparisons());
        metrics.addComparison();
        assertEquals(1, metrics.getComparisons());
        metrics.addComparison();
        assertEquals(2, metrics.getComparisons());
    }

    @Test
    void testAddSwap() {
        Metrics metrics = new Metrics();
        assertEquals(0, metrics.getSwaps());
        metrics.addSwap();
        assertEquals(1, metrics.getSwaps());
        metrics.addSwap();
        assertEquals(2, metrics.getSwaps());
    }

    @Test
    void testDepth() {
        Metrics metrics = new Metrics();
        assertEquals(0, metrics.getMaxDepth());
        assertEquals(0, metrics.getCurrentDepth());
        metrics.enter();
        metrics.enter();
        metrics.enter();
        metrics.exit();
        metrics.exit();
        assertEquals(1, metrics.getCurrentDepth());
        assertEquals(3, metrics.getMaxDepth());
    }

    @Test
    void testTime() throws InterruptedException {
        Metrics metrics = new Metrics();
        metrics.start();
        Thread.sleep(10);
        metrics.stop();
        assertTrue(metrics.getTime() > 0);
    }

    @Test
    void testReset() {
        Metrics metrics = new Metrics();
        metrics.reset();
        assertEquals(0, metrics.getComparisons());
        assertEquals(0, metrics.getSwaps());
        assertEquals(0, metrics.getCurrentDepth());
        assertEquals(0, metrics.getMaxDepth());
        assertEquals(0, metrics.getTime());
    }
}