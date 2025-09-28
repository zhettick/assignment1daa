package org.sabulla;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ClosestPair {
    static Point closestA;
    static Point closestB;

    public static double closestPair(Point[] points, Metrics metrics) {
        metrics.reset();
        metrics.start();

        if (points.length < 2) {
            throw new IllegalArgumentException("At least 2 points required");
        }

        Arrays.sort(points, Comparator.comparingInt(Point::getX));

        double minDistance = closestPairRecursive(points, 0, points.length - 1, metrics);

        metrics.stop();

        System.out.println("Closest pair: (" + closestA.getX() + ", " + closestA.getY() + ") and (" + closestB.getX() + ", " + closestB.getY() + "), distance = " + minDistance);

        return minDistance;
    }

    public static double closestPairRecursive(Point[] points, int start, int end, Metrics metrics) {
        if (end - start + 1 <= 3) {
            double min = Double.MAX_VALUE;
            for (int i = start; i <= end; i++) {
                for (int j = i + 1; j <= end; j++) {
                    double d = distanceSquare(points[i], points[j]);
                    metrics.addComparison();
                    if (d < min) {
                        min = d;
                        closestA = points[i];
                        closestB = points[j];
                    }
                }
            }
            return Math.sqrt(min);
        }

        metrics.enter();

        int mid = (start + end) / 2;
        double minRightDist = closestPairRecursive(points, mid + 1, end, metrics);
        double minLeftDist = closestPairRecursive(points, start, mid, metrics);
        double minDistance = Math.min(minRightDist, minLeftDist);
        double minDistanceSquared = minDistance * minDistance;

        List<Point> stripList = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            metrics.addComparison();
            if (Math.pow(points[i].getX() - points[mid].getX(), 2) < minDistanceSquared) {
                stripList.add(points[i]);
            }
        }

        Point[] strip = stripList.toArray(new Point[0]);
        Arrays.sort(strip, Comparator.comparingInt(Point::getY));

        double minStrip = minDistanceSquared;

        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && Math.pow(strip[j].getY() - strip[i].getY(), 2) < minStrip; j++) {
                metrics.addComparison();
                double dist = distanceSquare(strip[i], strip[j]);
                if (dist < minStrip) {
                    minStrip = dist;
                    closestA = strip[i];
                    closestB = strip[j];
                }
            }
        }

        metrics.exit();

        return Math.sqrt(Math.min(minDistanceSquared, minStrip));
    }

    private static double distanceSquare(Point a, Point b) {
        int dx = a.getX() - b.getX();
        int dy = a.getY() - b.getY();
        return dx * dx + dy * dy;
    }
}
