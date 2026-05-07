package dijkstra;

import java.util.HashMap;
import java.util.Map;

public class CityNode {
    private String name;
    private Map<CityNode, Integer> neighbours;


    public CityNode (String name) {
        this.name = name;
        this.neighbours = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Map<CityNode, Integer> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour (CityNode neighbour, int distance) {
        neighbours.put(neighbour, distance);
    }


}
