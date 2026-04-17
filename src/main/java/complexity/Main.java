package complexity;

import com.sun.source.util.Trees;
import complexity.entities.Resident;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        int[] n = {1, 2, 4, 10, 2};

        // Opgave 1: O(1) eksempel:
        int result = add(n);
        System.out.println("O(1) eksempel sum: " + result);

        // Opgave 1: O(n) eksempel:
        int result2 = add2(n);
        System.out.println("O(n) eksempel sum: " + result2);

        // Opgave 1: O log(n) eksempel:
        int result3 = multiply(100);
        System.out.println("log(n) eksempel sum: " + result3);


        // Opgave 2: ArrayList vs LinkedList:
        arrayListVSLinkedList(500000);

        // Opgave 2: ArrayList vs Hashset:
        arrayListVSHashSet(500000);

        // Opgave 2: TreeSet vs Hashset:
        treeSetVSHashSet();
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
        System.out.println("Antal operationer O(n): " + count);
        return sum;
    }

    // O(log n) Logaritmisk tid
    public static int multiply (int n) {
        int count = 0;
        int sum = 0;

        for (int i = 1; i < n; i *= 2) {
            sum += i;
            count++;
            System.out.println("Sum for hver operation O(log n): " + sum);
        }
        System.out.println("Antal operationer O(log n): " + count);
        return sum;
    }

    // Opgave 2
    // ArrayList vs LinkedList
    public static void arrayListVSLinkedList (int n) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        // Hent element i midten ArrayList
        long start = System.currentTimeMillis();
        int middle = arrayList.get(n/2);
        long stop = System.currentTimeMillis();
        System.out.println("Tid for hentning af element: ArrayList O(1): " + (stop - start) + " ms");
        // Konstant tid O(1): Elementet findes ved 1 operation da vi har en direkte reference til placering i hukommelsen via indexet

        // Hent element i midten LinkedList
        start = System.currentTimeMillis();
        int middle2 = linkedList.get(n/2);
        stop = System.currentTimeMillis();
        System.out.println("Tid for hentning af element: LinkedList O(n): " + (stop - start) + " ms");
        // Lineær tid O(n): Elementerne i listen kender kun til deres naboer og vi skal derfor igennem hvert element indtil vi når midten

        // Indsæt element midt i listen ArrayList
        start = System.currentTimeMillis();
        arrayList.add(n/2, 99);
        stop = System.currentTimeMillis();
        System.out.println("Tid for indsætning af element: ArrayList O(n): " + (stop - start) + " ms");
        // O(1) konstant tid for at finde midsten, men når vi indsætter så skal alt efter midten f
        // O(n) når alt så skal rykkes en plads til højre, så hvert element får en ny indexplacering

        // Indsæt element midt i listen LinkedList
        start = System.currentTimeMillis();
        linkedList.add(n/2, 99);
        stop = System.currentTimeMillis();
        System.out.println("Tid for indsætning af element: LinkeList O(n): " + (stop - start) + " ms");
        // Lineær tid O(n) for at finde midten så skal vi igennem alle elementer indtil vi når midten
        // konstant tid O(1) når vi så indsætter noget i midten

        // Slet element midt i listen ArrayList
        start = System.currentTimeMillis();
        arrayList.remove(n/2);
        stop = System.currentTimeMillis();
        System.out.println("Tid for sletning af element: ArrayList O(n): " + (stop - start) + " ms");
        // Konstant tid O(1) for at finde midten
        // Lineær tid O(n) for at rykke alle elementer én plads mod venstre for at ændre indexplacering

        // Slet element midt i listen LinkedList
        start = System.currentTimeMillis();
        linkedList.remove(n/2);
        stop = System.currentTimeMillis();
        System.out.println("Tid for sletning af element: LinkedList O(n): " + (stop - start) + " ms");
        // Lineær tid O(n) for at finde midten
        // Konstant tid O(1) for at lave nye referencer til naboelementerne for at "lappe" hullet
    }

    // Opgave 2: Søgning i List vs Hashset
    public static void arrayListVSHashSet (int n) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        HashSet<Integer> hashSet = new HashSet<>();

        for (int i = 0; i < n; i++) {
            arrayList.add(i);
            hashSet.add(i);
        }

        // Søgning på element der findes ArrayList:
        long start = System.currentTimeMillis();
        boolean result = arrayList.contains(n / 2);
        long stop = System.currentTimeMillis();
        System.out.println("Tid for .contains() af element der findes: Arraylist O(n): " + (stop - start) + " ms");
        // Lineær tid O(n) da vi skal loope hvert element igennem for at se om det indeholder den søgte værdi

        // Søgning på element der findes LinkedList:
        start = System.currentTimeMillis();
        boolean result2 = hashSet.contains(n / 2);
        stop = System.currentTimeMillis();
        System.out.println("Tid for .contains() af element der findes: HashSet O(1): " + (stop - start) + " ms");
        // Konstant tid O(1), da vi hasher værdien og via hash koden finder adressen i hukommelsen og ser om elementer er der

        // Søgning på element der ikke findes arrayList:
        start = System.currentTimeMillis();
        boolean result3 = arrayList.contains(n + 1);
        stop = System.currentTimeMillis();
        System.out.println("Tid for .contains() af element der IKKE findes: Arraylist O(n): " + (stop - start) + " ms");
        // Lineær tid O(n), vi looper igennem hele listen for at se om elementet er deri

        // Søgning på element der ikke findes linkedList:
        start = System.currentTimeMillis();
        boolean result4 = hashSet.contains(n + 1);
        stop = System.currentTimeMillis();
        System.out.println("Tid for .contains() af element der IKKE findes: HashSet O(1): " + (stop - start) + " ms");
        // Konstant tid O(1), vi hasher værdien, kigger og ser at der ikke findes noget i hukommelsen
    }


    public static void treeSetVSHashSet () {
        Resident r1 = new Resident("Jensen", 1,  "Jens");
        Resident r2 = new Resident("Mortensen", 2, "Morten");
        Resident r3 = new Resident("Børgesen", 3, "Børge");

        HashSet<Resident> hashSet = new HashSet<>();
        TreeSet<Resident> treeSet = new TreeSet<>();

        hashSet.add(r1);
        hashSet.add(r2);

        treeSet.add(r1);
        treeSet.add(r2);


        // Add, HashSet
        long start = System.nanoTime();
        boolean result = hashSet.add(r3);
        long stop = System.nanoTime();
        System.out.println("Tid for .add hashSet O(1): " + (stop - start) + " ns");

        // Add, TreeSet
        start = System.nanoTime();
        boolean result2 = treeSet.add(r3);
        stop = System.nanoTime();
        System.out.println("Tid for .add treeSet O(log n): " + (stop - start) + " ns");

        // Get, HashSet
        start = System.nanoTime();
        boolean result3 = hashSet.contains(r1);
        stop = System.nanoTime();
        System.out.println("Tid for .contains() HashSet O(1): " + (stop - start) + " ns");

        // Get, TreeSet
        start = System.nanoTime();
        boolean result4 = treeSet.contains(r1);
        stop = System.nanoTime();
        System.out.println("Tid for .contains() TreeSet O(log n): " + (stop - start) + " ns");

        // Remove, HashSet
        start = System.nanoTime();
        boolean result5 = hashSet.remove(r1);
        stop = System.nanoTime();
        System.out.println("Tid for .remove() HashSet O(1): " + (stop - start) + " ns");

        // Remove, TreeSet
        start = System.nanoTime();
        boolean result6 = treeSet.remove(r1);
        stop = System.nanoTime();
        System.out.println("Tid for .remove() TreeSet O(log n): " + (stop - start) + " ns");

        // HashSet er hurtigere i alle operationer, da den bare skal hashe værdien og finde "bucketen" i hukommelsen,
        // mens at treeSet skal compareTo() hver gang den skal "vælge vej" i træet.
    }


}
