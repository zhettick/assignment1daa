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
        if (points.length == 1) {
            throw new IllegalArgumentException("At least should be 2 points");
        } else if (points.length <= 3) {
            double min = Double.MAX_VALUE;
            for (int i = 0; i < points.length; i++) {
                for (int j = i + 1; j < points.length; j++) {
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
        Arrays.sort(points, Comparator.comparingInt(Point::getX));
        int mid = points.length / 2;
        Point[] left = Arrays.copyOfRange(points, 0, mid);
        Point[] right = Arrays.copyOfRange(points, mid, points.length);
        double minRight = closestPair(right, metrics);
        double minLeft = closestPair(left, metrics);
        double minDistance = Math.min(minRight * minRight, minLeft * minLeft);
        List<Point> stripList = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            metrics.addComparison();
            if (Math.pow(points[i].getX() - points[mid].getX(), 2) < minDistance) {
                stripList.add(points[i]);
            }
        }
        Point[] strip = stripList.toArray(new Point[0]);
        Arrays.sort(strip, Comparator.comparingInt(Point::getY));
        double minStrip = minDistance;
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
        metrics.stop();
        System.out.println("Closest pair: (" + closestA.getX() + ", " + closestA.getY() + ") and (" + closestB.getX() + ", " + closestB.getY() + ")");
        return Math.sqrt(Math.min(minDistance, minStrip));
    }

    private static double distanceSquare(Point a, Point b) {
        int dx = a.getX() - b.getX();
        int dy = a.getY() - b.getY();
        return dx * dx + dy * dy;
    }
}
