package designpatterns.strategy;

import designpatterns.strategy.heuristics.EuclideanHeuristic;
import designpatterns.strategy.heuristics.ManhattanHeuristic;

import java.util.*;

public class Main {
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

        Astar astar = new Astar();

        astar.setHeuristic(new ManhattanHeuristic());
        astar.findShortestPath(source, destination);

        astar.setHeuristic(new EuclideanHeuristic());
        astar.findShortestPath(source, destination);

    }
}
