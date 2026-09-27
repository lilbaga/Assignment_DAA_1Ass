package src;

import java.util.Random;

public class QuickSorter {
    private long comparisonCount = 0;
    private long maxRecursionDepth = 0;
    private final Random random = new Random();

    public void sort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        comparisonCount = 0;
        maxRecursionDepth = 0;
        quickSort(arr, 0, arr.length - 1, 1);
    }

    private void quickSort(int[] arr, int low, int high, long depth) {
        if (depth > maxRecursionDepth) {
            maxRecursionDepth = depth;
        }

        while (low < high) {
            int pivotIndex = low + random.nextInt(high - low + 1);
            int[] bounds = partition(arr, low, high, pivotIndex);
            int lt = bounds[0];
            int gt = bounds[1];

            if (lt - low < high - gt) {
                quickSort(arr, low, lt - 1, depth + 1);
                low = gt + 1;
            } else {
                quickSort(arr, gt + 1, high, depth + 1);
                high = lt - 1;
            }
        }
    }

    private int[] partition(int[] arr, int low, int high, int pivotIndex) {
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, high);
        
        int i = low;
        int lt = low;
        int gt = high;

        while (i <= gt) {
            comparisonCount++;
            if (arr[i] < pivot) {
                swap(arr, lt++, i++);
            } else if (arr[i] > pivot) {
                swap(arr, i, gt--);
            } else {
                i++;
            }
        }
        return new int[] { lt, gt };
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public long getComparisonCount() {
        return comparisonCount;
    }

    public long getMaxRecursionDepth() {
        return maxRecursionDepth;
    }
}