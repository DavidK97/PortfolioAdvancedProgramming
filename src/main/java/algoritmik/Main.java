package algoritmik;


import java.util.List;

public class Main {
    // Opgave 3: Backtracking
    static final int N = 6;

    static int[][] maze = {
            {1, 0, 1, 1, 1, 0},
            {1, 1, 1, 0, 1, 0},
            {0, 0, 1, 0, 1, 1},
            {0, 1, 1, 1, 0, 1},
            {0, 1, 0, 0, 0, 1},
            {0, 1, 1, 1, 1, 1}
    };

    static int[][] path = new int[N][N];


    public static void main(String[] args) {
        // Opgave 2: Cycle Detector
        Node list = ListFactory.buildList(1, 2, 3, 4, 5);
        if(hasCycle(list)) {
            System.out.println("Listen er cyklisk");
        } else {
            System.out.println("Liste 1 er ikke cyklisk: " + list);
        }

        Node circularList = ListFactory.buildListWithCycle();
        if(hasCycle(circularList)) {
            System.out.println("Liste 2 er cyklisk");
        } else {
            System.out.println("Liste 2 er ikke cyklisk");
        }



        // Opgave 3: Backtracking
        if (solveMaze(0, 0)) {
            printPath();
        } else {
            System.out.println("Ingen løsning fundet.");
        }
    }






    // Opgave 2: CycleDetector
    public static boolean hasCycle (Node head) {
        Node slow = head;
        Node fast = head;

        while (true) {
            // Skildpaddens hop
            if (slow == null) return false; // hvis null så er den ikke cyklisk
            slow = slow.next;

            // Hare første hop
            if (fast == null) return false;
            fast = fast.next;

            // Hare andet hop
            if (fast == null) return false;
            fast = fast.next;

            // Hvis de mødes
            if (slow == fast) return true;

            // Hvis skilpadden stod stille og haren hoppede med 1,
            // så ville man ikke kunne detecte en cirkulær liste som starter som en non-cirkulær liste,
            // Fordi kun haren ender i cirklen.
        }
    }


        // Opgave 3: Backtracking
        static boolean solveMaze(int row, int col) {

            // Tjek om man er out of bounds
            if (row > N - 1 || row < 0 || col > N - 1 || col < 0 ) return false;

            // Tjek om feltet er prøvet før
            if (path[row][col] == 1) return false;

            // Tjek om felt er gyldigt
            if (maze[row][col] == 0) return false;

            // Marker felt som en del af stien
            path[row][col] = 1;

            // Tjek om målet er nået
            if (row == N - 1 && col == N - 1) return true;


            // Prøv de 4 retninger
            // Ned
            if (solveMaze(row + 1, col)) return true;

            // Højre
            if (solveMaze(row, col + 1)) return true;

            // Op
            if (solveMaze(row - 1, col)) return true;

            // Venstre
            if (solveMaze(row, col - 1)) return true;

            // Backtrack
            path[row][col] = 0;

            return false;
        }

        static void printPath() {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(path[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
