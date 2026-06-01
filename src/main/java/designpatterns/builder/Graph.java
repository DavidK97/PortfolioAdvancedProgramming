package designpatterns.builder;

import designpatterns.strategy.CityNode;
import java.util.HashMap;

public class Graph {
    private CityNode[][] nodes;
    private HashMap<String, CityNode> cities;

    private Graph(Builder builder) {
        this.cities = builder.cities;
        this.nodes = builder.nodes;
    }

    public CityNode getCity(String name) {
        return cities.get(name);
    }

    public static class Builder {
        private int[][] grid;
        private CityNode[][] nodes;
        private HashMap<String, CityNode> cities = new HashMap<>();
        private int ROWS;
        private int COLS;

        public Builder addGrid (int[][] grid) {
            this.grid = grid;
            this.ROWS   = grid.length;
            this.COLS   = grid[0].length;
            this.nodes = new CityNode[ROWS][COLS];
            buildNodes();
            connectNeighbours();
            return this;
        }

        public Builder addCity (String name, int row, int col) {
            cities.put(name, nodes[row][col]);
            return this;
        }

        public Graph build () {
            return new Graph(this);
        }


        private void buildNodes () {
            for (int row = 0; row < ROWS; row++) {
                for(int column = 0; column < COLS; column++) {
                    if (grid[row][column] == 0) {
                        nodes[row][column] = new CityNode(row, column);
                    }
                }
            }
        }

        private void connectNeighbours () {
            int [][] directions = {
                    {-1,0},
                    {1,0},
                    {0,-1},
                    {0,1}
            };

            for (int row = 0; row < ROWS; row++) {
                for (int column = 0; column < COLS; column++) {

                    if (nodes[row][column] == null) continue;

                    for (int[] d : directions) {
                        int nr = row + d[0];
                        int nc = column + d[1];

                        if (nr >= 0 && nr < ROWS &&
                                nc >= 0 && nc < COLS &&
                                nodes[nr][nc] != null) {

                            nodes[row][column].addNeighbour(nodes[nr][nc]);
                        }
                    }
                }
            }
        }

    }



}
