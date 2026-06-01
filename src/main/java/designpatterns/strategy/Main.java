package designpatterns.strategy;

import designpatterns.builder.Graph;
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


    public static void main(String[] args) {

        Graph graph = new Graph.Builder()
                .addGrid(grid)
                .addCity("Nordby", 0, 0)
                .addCity("Sydby",  6, 5)
                .addCity("Østby",  2, 7)
                .addCity("Vestby", 4, 1)
                .build();


        CityNode source = graph.getCity("Østby");
        CityNode destination = graph.getCity("Sydby");


        Astar astar = new Astar();

        System.out.println("Manhattan: ");
        astar.setHeuristic(new ManhattanHeuristic());
        astar.findShortestPath(source, destination);

        System.out.println("Euclidean: ");
        astar.setHeuristic(new EuclideanHeuristic());
        astar.findShortestPath(source, destination);

    }
}
