package src;

import java.util.Arrays;

public class MergeSorter {
    private static final int INSERTION_SORT_THRESHOLD = 7; 
   
    private long comparisonCount = 0;
    private long maxRecursionDepth = 0;

    public void sort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        comparisonCount = 0;
        maxRecursionDepth = 0;
        int[] aux = new int[arr.length]; 
        sort(arr, aux, 0, arr.length - 1, 1);
    }

    private void sort(int[] arr, int[] aux, int low, int high, long depth) {
        if (depth > maxRecursionDepth) {
            maxRecursionDepth = depth;
        }

        
        if (high <= low + INSERTION_SORT_THRESHOLD) {
            insertionSort(arr, low, high);
            return;
        }

        int mid = low + (high - low) / 2;
        sort(arr, aux, low, mid, depth + 1);
        sort(arr, aux, mid + 1, high, depth + 1);

        
        if (arr[mid] <= arr[mid + 1]) {
            return;
        }

        merge(arr, aux, low, mid, high);
    }

    private void merge(int[] arr, int[] aux, int low, int mid, int high) {
        
        for (int i = low; i <= high; i++) {
            aux[i] = arr[i];
        }

        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            comparisonCount++;
            if (i > mid) {
                arr[k] = aux[j++];
            } else if (j > high) {
                arr[k] = aux[i++];
            } else if (aux[j] < aux[i]) {
                arr[k] = aux[j++];
            } else {
                arr[k] = aux[i++];
            }
        }
    }

    private void insertionSort(int[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= low && arr[j] > temp) {
                comparisonCount++;
                arr[j + 1] = arr[j];
                j--;
            }
            if (j >= low) {
                comparisonCount++; 
            }
            arr[j + 1] = temp;
        }
    }

    public long getComparisonCount() {
        return comparisonCount;
    }

    public long getMaxRecursionDepth() {
        return maxRecursionDepth;
    }
}