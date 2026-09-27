package src;

import java.util.Arrays;

public class DeterministicSelector {

    public int select(int[] arr, int k) {
        if (arr == null || k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        int[] copy = arr.clone();
        return select(copy, 0, copy.length - 1, k);
    }

    private int select(int[] arr, int low, int high, int k) {
        while (true) {
            if (low == high) {
                return arr[low];
            }

            int pivotIndex = medianOfMedians(arr, low, high);
            pivotIndex = partition(arr, low, high, pivotIndex);

            if (k == pivotIndex) {
                return arr[k];
            } else if (k < pivotIndex) {
                high = pivotIndex - 1;
            } else {
                low = pivotIndex + 1;
            }
        }
    }

    private int medianOfMedians(int[] arr, int low, int high) {
        int numElements = high - low + 1;
        if (numElements <= 5) {
            return insertionSortAndGetMedian(arr, low, high);
        }

        int numGroups = (numElements + 4) / 5;
        for (int i = 0; i < numGroups; i++) {
            int groupLow = low + i * 5;
            int groupHigh = Math.min(groupLow + 4, high);
            int medianIndex = insertionSortAndGetMedian(arr, groupLow, groupHigh);
            int targetIndex = low + i;
            swap(arr, medianIndex, targetIndex);
        }

        int medianOfMediansIndex = low + numGroups / 2;
        return selectForMedianOfMedians(arr, low, low + numGroups - 1, medianOfMediansIndex);
    }

    private int selectForMedianOfMedians(int[] arr, int low, int high, int k) {
        if (low == high) return low;
        int pivotIndex = medianOfMedians(arr, low, high);
        pivotIndex = partition(arr, low, high, pivotIndex);
        if (k == pivotIndex) return k;
        else if (k < pivotIndex) return selectForMedianOfMedians(arr, low, pivotIndex - 1, k);
        else return selectForMedianOfMedians(arr, pivotIndex + 1, high, k);
    }

    private int insertionSortAndGetMedian(int[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= low && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
        return low + (high - low) / 2;
    }

    private int partition(int[] arr, int low, int high, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, high);
        int storeIndex = low;
        for (int i = low; i < high; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, high, storeIndex);
        return storeIndex;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}