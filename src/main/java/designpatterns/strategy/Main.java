package designpatterns.strategy;

import java.util.*;

public class Main {
     private static HeuristicStrategy currentHeuristic;

    public static void setHeuristic(HeuristicStrategy heuristic) {
        currentHeuristic = heuristic;
    }

    // 0 = veje og 1 = blokeret
    static int[][] grid = {
            {0,0,1,1,0,0,0,0},
            {1,0,0,0,0,1,1,0},
            {1,0,1,1,0,1,1,0},
            {1,0,1,1,0,0,0,0},
            {1,0,1,1,0,1,1,0},
            {1,0,0,0,0,0,1,0},
            {1,0,1,1,1,0,0,0},
            {1,0,0,1,1,1,1,1}
    };

    static final int ROWS = 8;
    static final int COLS = 8;

    static Map<String, CityNode> cities = new HashMap<>();


    public static void main(String[] args) {

        // Lav alle noder
        CityNode[][] nodes = new CityNode[ROWS][COLS];

        for (int row = 0; row < ROWS; row++) {
            for(int column = 0; column < COLS; column++) {
                if (grid[row][column] == 0) {
                    nodes[row][column] = new CityNode(row, column);
                }
            }
        }

        // Lav edges/forbind til naboer
        int [][] directions = {
                {-1,0}, // op
                {1,0},  // ned
                {0,-1}, // venstre
                {0,1}   // højre
        };

        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLS; column++) {

                if (nodes[row][column] == null) continue;

                // Loop tjekker hver direction på nuværende node for naboer
                for (int[] d : directions) {
                    int nr = row + d[0];
                    int nc = column + d[1];

                    // Tjek om vi er inden for grids grænser og at naboen er en node
                    if (nr >= 0 && nr < ROWS &&
                            nc >= 0 && nc < COLS &&
                            nodes[nr][nc] != null) {

                        nodes[row][column].addNeighbour(nodes[nr][nc]);
                    }
                }
            }
        }

        cities.put("Nordby", nodes[0][0]);
        cities.put("Sydby", nodes[6][5]);
        cities.put("Østby", nodes[2][7]);
        cities.put("Vestby", nodes[4][1]);

        CityNode source = cities.get("Østby");
        CityNode destination = cities.get("Sydby");

        System.out.println("Manhattan");
        setHeuristic(new ManhattanHeuristic());
        findShortestPath(source, destination);

        System.out.println("Euclidean: ");
        setHeuristic(new EuclideanHeuristic());
        findShortestPath(source, destination);

    }

    // Algoritmen
    private static void findShortestPath (CityNode source, CityNode destination) {
        Map<CityNode, CityNode> prev = new HashMap<>();
        Map<CityNode, Integer> dist = new HashMap<>();
        Set<CityNode> visited = new HashSet<>();

        PriorityQueue<CityNodeWithDist> queue = new PriorityQueue<>();

        queue.add(new CityNodeWithDist(
                source,
                0,
                currentHeuristic.calculateHeuristic(source, destination)));
        dist.put(source, 0);

        while (!queue.isEmpty()) {
            CityNodeWithDist current = queue.poll();

            if (current.cityNode.equals(destination)) break;

            if (visited.contains((current.cityNode))) continue;

            visited.add(current.cityNode);

            for (CityNode neighbour : current.cityNode.getNeighbours()) {
                if (visited.contains(neighbour)) continue;

                int newDist = current.gCost + 1;

                if (newDist < dist.getOrDefault(neighbour, Integer.MAX_VALUE)) {
                    dist.put(neighbour, newDist);
                    prev.put(neighbour, current.cityNode);

                    queue.add(new CityNodeWithDist(
                            neighbour,
                            newDist,
                            currentHeuristic.calculateHeuristic(neighbour, destination)));
                }
            }
        }

        // Rekonstruer hurtigste path
        List<String> path = new ArrayList<>();
        CityNode step = destination;

        while (step != null) {
            path.add(0, "(" + step.getRow() + "," + step.getColumn() + ")");
            step = prev.get(step);
        }

        System.out.println("Korteste vej: " + path);
        System.out.println("Antal skridt: " + (path.size() - 1));
    }



    private static class CityNodeWithDist implements Comparable<CityNodeWithDist> {
        CityNode cityNode;
        int gCost;
        int fCost;

        public CityNodeWithDist(CityNode cityNode, int gCost, int hCost) {
            this.cityNode = cityNode;
            this.gCost = gCost;
            this.fCost = gCost + hCost;
        }

        @Override
        public int compareTo(CityNodeWithDist other) {
            return Integer.compare(this.fCost, other.fCost);
        }
    }

}
