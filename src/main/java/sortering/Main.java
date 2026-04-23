package sortering;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        //Bubble Sort
        int[] arr0 = {1, 2, 4, 2 ,5, 5, 10, 5, 7};
        bubbleSort(arr0);
        System.out.println(Arrays.toString(arr0));

        // MergeSort
        int[] arr1 = {1, 2, 4, 2 ,5, 5, 10, 5, 7};
        mergeSort(arr1);
        System.out.println(Arrays.toString(arr1));

        // Quick Sort
        int[] arr2 = {1, 2, 4, 2 ,5, 5, 10, 5, 7};
        quickSort(arr2, 0, arr2.length - 1);
        System.out.println(Arrays.toString(arr2));

    }


    // Ydre løkke sørger for at vi får gennemløbet hele arrayet.
    // I hver iteration bobler det nuværende højeste tal så langt til højre som det kan komme
    // Indre lykke laver selve sammenligningen mellem 2 tal fra venstre mod højre, så det bobler til højre
    public static void bubbleSort (int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) { // n - i fordi vi ikke behøver kigge på yderste kort til højre fra sidste iteration
                if (arr[j] > arr[j + 1]) { // Sammenligner det på j'te plads med tallet til højre for
                    swap(arr, j, j + 1); // Bytter deres placering hvis tallet til venstre er størst
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void mergeSort (int[] arr) {
        // (Basecase) Condition der bryder rekursivt kald når arrays ikke kan divides mere
        if (arr.length < 2) {
            return;
        }
        // Del i left og right
        int middle = arr.length / 2;
        int[] leftHalf = new int[middle];
        int[] rightHalf = new int[arr.length - middle];

        // Kopier indhold ind i leftHalf:
        for (int i = 0; i < middle; i++) {
            leftHalf[i] = arr[i];
        }
        // Kopier indhold ind i rightHalf:
        for (int i = middle; i < arr.length; i++) {
            rightHalf[i - middle] = arr[i];
        }

        // Rekursivt kald indtil alle arrays er divided
        mergeSort(leftHalf); // Først divides leftHalf helt færdigt og lave nye left and rights og merger dem
        mergeSort(rightHalf); // Dernæst tages den højre bunke og divides og merges og så merges de 2 "oprindelige bunker"

        // Når alt er diveded, så skal arraysne merges sammen
        merge(arr, leftHalf, rightHalf);
    }

    public static void merge (int[] input, int[] leftHalf, int[] rightHalf) {
        int i = 0, l = 0, r = 0;

        // Loop kører så længe der er ints i leftHalf og rightHalf array
        while (l < leftHalf.length && r < rightHalf.length) {
            if (leftHalf[l] <= rightHalf[r]) { // Sammenligning af venstre og højre side
                input[i] = leftHalf[l]; // Input sættes til left, hvis left er mindst
                l++;
            } else {
                input[i] = rightHalf[r]; // Input sættes til right, hvis right er mindst
                r++;
            }
            i++;
        }
        // Hvis vi har resterende ints i enten leftHalf eller rightHalf
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

    /* QuickSort */
    // 'i' bestemmer hvor p skal stå. Da i holder øje med hvor mange kort der er mindre en p.
    // For hvert swap (hvis et tal er mindre end p), så stiger i
    // Når et helt sæt er kørt fra low til high, så skal der byttes på p og i

    public static void quickSort (int[] arr, int low, int high) {
        // Basecase. Rekursivt kald stopper hvis low er >= high, hvis der kun er 1 element tilbage
        if (low < high) {
            // Rekursivt kald
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot + 1, high);
        }
    }

    public static int partition (int[] arr, int low, int high) {
        int i = low - 1; // i er index for det sidste element, som er mindre end pivot
        int pivotValue = arr[high]; // Sidste element i array er pivot

        // Loop kører så array fra low til lige før pivotValue
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivotValue) { // Tjekker om det på j'te plads er mindre en pivot
                i++; // Hvis mindre, så kigger vi på næste element i rækken
                swap(arr, i, j); // Rykker det mindre tal til venstre
            }
        }
        // Efter alle tal er tjekket igennem så skal pivot sættes på pladsen til højre for i
        swap(arr, i + 1, high);
        return i + 1; // Den nye pivot position
    }
}
