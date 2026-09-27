package src;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPairSolver {

    public double findClosestPair(Point[] points) {
        if (points == null || points.length < 2) {
            throw new IllegalArgumentException("At least 2 points required");
        }

        Point[] pointsSortedX = points.clone();
        Point[] pointsSortedY = points.clone();

        Arrays.sort(pointsSortedX);
        Arrays.sort(pointsSortedY, Comparator.comparingDouble(Point::getY));

        return closestPairRecursive(pointsSortedX, pointsSortedY, 0, points.length - 1);
    }

    private double closestPairRecursive(Point[] px, Point[] py, int low, int high) {
        if (high - low <= 3) {
            return bruteForce(px, low, high);
        }

        int mid = low + (high - low) / 2;
        Point midPoint = px[mid];

        Point[] pyl = new Point[mid - low + 1];
        Point[] pyr = new Point[high - mid];
        int li = 0, ri = 0;

        for (Point p : py) {
            if (p.getX() <= midPoint.getX() && li < pyl.length) {
                pyl[li++] = p;
            } else {
                if (ri < pyr.length) {
                    pyr[ri++] = p;
                }
            }
        }

        double dl = closestPairRecursive(px, pyl, low, mid);
        double dr = closestPairRecursive(px, pyr, mid + 1, high);
        double d = Math.min(dl, dr);

        Point[] strip = new Point[px.length];
        int stripSize = 0;
        for (Point p : py) {
            if (Math.abs(p.getX() - midPoint.getX()) < d) {
                strip[stripSize++] = p;
            }
        }

        double minMin = d;
        for (int i = 0; i < stripSize; i++) {
            for (int j = i + 1; j < stripSize && (strip[j].getY() - strip[i].getY()) < minMin; j++) {
                double distance = strip[i].distanceTo(strip[j]);
                if (distance < minMin) {
                    minMin = distance;
                }
            }
        }

        return minMin;
    }

    private double bruteForce(Point[] px, int low, int high) {
        double min = Double.MAX_VALUE;
        for (int i = low; i <= high; i++) {
            for (int j = i + 1; j <= high; j++) {
                double dist = px[i].distanceTo(px[j]);
                if (dist < min) {
                    min = dist;
                }
            }
        }
        return min;
    }
}