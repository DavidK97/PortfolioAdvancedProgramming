package sortering;

import java.util.Arrays;

public class Main {

    // Hvad er kompleksiteten for de forskellige sort-algoritmer?
    // Hvad med heap sort?

    public static void main(String[] args) {
        //Bubble Sort
        int[] arr0 = {1, 4, 2, 5, 10, 7};
        bubbleSort(arr0);
        System.out.println(Arrays.toString(arr0));

        // MergeSort
        int[] arr1 = {1, 4, 2, 5, 10, 7};
        mergeSort(arr1);
        System.out.println(Arrays.toString(arr1));

        // Quick Sort
        int[] arr2 = {1, 4, 2, 5, 10, 7};
        quickSort(arr2, 0, arr2.length - 1);
        System.out.println(Arrays.toString(arr2));

    }


    public static void bubbleSort (int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) { // Hvorfor n - 1?
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i]; // Hvorfor er temp nødvendig?
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void mergeSort (int[] arr) {

        if (arr.length < 2) {
            return;
        }

        // Divide
        int middle = arr.length / 2;
        int[] leftHalf = new int[middle];
        int[] rightHalf = new int[arr.length - middle];

        // Kopier
        for (int i = 0; i < middle; i++) {
            leftHalf[i] = arr[i];
        }
        // Kopier
        for (int i = middle; i < arr.length; i++) {
            rightHalf[i - middle] = arr[i];
        }

        // Divide fortsættes
        mergeSort(leftHalf);
        mergeSort(rightHalf);

        merge(arr, leftHalf, rightHalf);
    }

    public static void merge (int[] input, int[] leftHalf, int[] rightHalf) {
        int i = 0, l = 0, r = 0;

        // Conquer
        while (l < leftHalf.length && r < rightHalf.length) {
            // Rangering
            if (leftHalf[l] <= rightHalf[r]) {
                input[i] = leftHalf[l];
                l++;
            } else {
                input[i] = rightHalf[r];
                r++;
            }
            i++;
        }
        // Resterende enkelte ints
        while (l < leftHalf.length) {
            input[i] = leftHalf[l];
            l++;
            i++;
        }
        while (r < rightHalf.length) {
            input[i] = rightHalf[r];
            r++;
            i++;
        }
    }

    // QuickSort
    public static void quickSort (int[] arr, int low, int high) {
        if (low < high) {

            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot + 1, high);
        }
    }

    public static int partition (int[] arr, int low, int high) {
        int i = low - 1;
        int pivotValue = arr[high];

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivotValue) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1; // Den nye pivot position
    }

    // Hvad er worst case i quicksort?
}
