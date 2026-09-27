package src;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Experiment {

    public static void runExperiments() {
        int[] sizes = {100, 500, 1000, 5000, 10000};
        String filePath = "results/results.csv";

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("Algorithm,InputType,Size,TimeNs,Comparisons,MaxDepth\n");

            Random random = new Random();
            MergeSorter mergeSorter = new MergeSorter();
            QuickSorter quickSorter = new QuickSorter();

            for (int size : sizes) {
                int[] randomArr = random.ints(size, -10000, 10000).toArray();

                // MergeSort Random
                int[] arr1 = randomArr.clone();
                long start = System.nanoTime();
                mergeSorter.sort(arr1);
                long time = System.nanoTime() - start;
                writer.append(String.format("MergeSort,Random,%d,%d,%d,%d\n", size, time, mergeSorter.getComparisonCount(), mergeSorter.getMaxRecursionDepth()));

                // QuickSort Random
                int[] arr2 = randomArr.clone();
                start = System.nanoTime();
                quickSorter.sort(arr2);
                time = System.nanoTime() - start;
                writer.append(String.format("QuickSort,Random,%d,%d,%d,%d\n", size, time, quickSorter.getComparisonCount(), quickSorter.getMaxRecursionDepth()));
            }

            writer.flush();
            System.out.println("Эксперименты успешно завершены! Результаты записаны в results/results.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}