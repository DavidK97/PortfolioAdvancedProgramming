package complexity;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        int[] n = {1, 2, 4, 10, 2};

        // Opgave 1: O(1) eksempel:
        int result = add(n);
        System.out.println(result);

        // Opgave 1: O(n) eksempel:
        int result2 = add2(n);
        System.out.println(result2);


        // Opgave 2: ArrayList vs LinkedList:
        opgave2(500000);
    }

    // Opgave 1
    // O(1) Konstant tid
    public static int add (int[] array) {
        int lastIndex = array.length - 1;
        return array[0] + array[lastIndex];
    }

    // O(n) Lineær tid
    public static int add2 (int[] array) {
        int sum = 0;
        int count = 0;
        for (int i : array) {
            sum += i;
            count++;
        }
        System.out.println("Antal operationer: " + count);
        return sum;
    }

    // O(log n) Logaritmisk tid


    // Opgave 2
    // ArrayList vs LinkedList
    public static void opgave2 (int n) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        // Hent element i midten
        long start = System.currentTimeMillis();
        int middle = arrayList.get(n/2);
        long stop = System.currentTimeMillis();
        System.out.println("Tid for hentning af element: ArrayList O(1): " + (stop - start) + " ms");

        start = System.currentTimeMillis();
        int middle2 = linkedList.get(n/2);
        stop = System.currentTimeMillis();
        System.out.println("Tid for hentning af element: LinkedList O(n): " + (stop - start) + " ms");

        // Indsæt element midt i listen
        start = System.currentTimeMillis();
        arrayList.add(n/2, 99);
        stop = System.currentTimeMillis();
        System.out.println("Tid for indsætning af element: ArrayList O(1) og O(n): " + (stop - start) + " ms");

        start = System.currentTimeMillis();
        linkedList.add(n/2, 99);
        stop = System.currentTimeMillis();
        System.out.println("Tid for indsætning af element: LinkeList O(n) og O(1): " + (stop - start) + " ms");

        // Slet element midt i listen
        start = System.currentTimeMillis();
        arrayList.remove(n/2);
        stop = System.currentTimeMillis();
        System.out.println("Tid for sletning af element: ArrayList O(1) og O(n): " + (stop - start) + " ms");

        start = System.currentTimeMillis();
        linkedList.remove(n/2);
        stop = System.currentTimeMillis();
        System.out.println("Tid for sletning af element: LinkedList O(n) og O(1): " + (stop - start) + " ms");

    }
}
