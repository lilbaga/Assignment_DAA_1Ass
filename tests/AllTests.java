package tests;

import src.*;
import java.util.Arrays;
import java.util.Random;

public class AllTests {
    public static void main(String[] args) {
        System.out.println("Запуск тестов проверки корректности...");

        testSortingAlgorithms();
        testDeterministicSelector();
        testClosestPair();

        System.out.println("Все тесты успешно пройдены!");
    }

    private static void testSortingAlgorithms() {
        MergeSorter mergeSorter = new MergeSorter();
        QuickSorter quickSorter = new QuickSorter();
        Random random = new Random();

        int[][] testCases = {
            {5, 2, 9, 1, 5, 6},
            {},
            {42},
            {1, 2, 3, 4, 5},
            {5, 4, 3, 2, 1},
            {7, 7, 7, 7, 7}
        };

        for (int i = 0; i < testCases.length; i++) {
            int[] original = testCases[i];
            
            int[] arr1 = original.clone();
            int[] arr2 = original.clone();
            int[] expected = original.clone();
            Arrays.sort(expected);

            mergeSorter.sort(arr1);
            quickSorter.sort(arr2);

            if (!Arrays.equals(arr1, expected) || !Arrays.equals(arr2, expected)) {
                throw new RuntimeException("Ошибка в сортировках на тесте " + i);
            }
        }
        System.out.println(" Тесты сортировок (MergeSort и QuickSort) пройдены.");
    }

    private static void testDeterministicSelector() {
        DeterministicSelector selector = new DeterministicSelector();
        Random random = new Random();

        for (int test = 0; test < 100; test++) {
            int size = 50 + random.nextInt(100);
            int[] arr = random.ints(size, -1000, 1000).toArray();
            int k = random.nextInt(size);

            int[] expectedArr = arr.clone();
            Arrays.sort(expectedArr);
            int expected = expectedArr[k];

            int actual = selector.select(arr, k);

            if (actual != expected) {
                throw new RuntimeException("Ошибка в DeterministicSelect на тесте " + test + ": ожидалось " + expected + ", получено " + actual);
            }
        }
        System.out.println(" 100 тестов для DeterministicSelect успешно пройдены.");
    }

    private static void testClosestPair() {
        ClosestPairSolver solver = new ClosestPairSolver();
        Random random = new Random();

        int n = 50;
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(random.nextDouble() * 100, random.nextDouble() * 100);
        }

        double expected = bruteForceClosestPair(points);
        double actual = solver.findClosestPair(points);

        if (Math.abs(expected - actual) > 1e-6) {
            throw new RuntimeException("Ошибка в ClosestPair: ожидалось " + expected + ", получено " + actual);
        }
        System.out.println(" Тест ClosestPair в сравнении с брутфорсом пройден.");
    }

    private static double bruteForceClosestPair(Point[] points) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double dist = points[i].distanceTo(points[j]);
                if (dist < min) {
                    min = dist;
                }
            }
        }
        return min;
    }
}