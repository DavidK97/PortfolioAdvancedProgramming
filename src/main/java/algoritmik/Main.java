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
            if (slow == null) return false;
            slow = slow.next;

            if (fast == null) return false;
            fast = fast.next;

            if (fast == null) return false;
            fast = fast.next;

            if (slow == fast) return true;
        }
    }


        // Opgave 3: Backtracking
        static boolean solveMaze(int row, int col) {

            // Tjek om man er out of bounds, er feltet afprøvet og om det er gyldigt
            if (row > N - 1 || row < 0 || col > N - 1 || col < 0 ) return false;
            if (path[row][col] == 1) return false;
            if (maze[row][col] == 0) return false;

            // Marker felt som en del af stien
            path[row][col] = 1;

            // Tjek om målet er nået
            if (row == N - 1 && col == N - 1) return true;


            // Prøv de 4 retninger
            if (solveMaze(row + 1, col)) return true;
            if (solveMaze(row, col + 1)) return true;
            if (solveMaze(row - 1, col)) return true;
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
