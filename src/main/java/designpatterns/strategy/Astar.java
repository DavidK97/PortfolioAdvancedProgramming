package designpatterns.strategy;

import designpatterns.strategy.heuristics.HeuristicStrategy;

import java.util.*;

public class Astar {
    private HeuristicStrategy heuristic;

    public void setHeuristic(HeuristicStrategy heuristic) {
        this.heuristic = heuristic;
    }

    public void findShortestPath(CityNode source, CityNode destination) {
        Map<CityNode, CityNode> prev = new HashMap<>();
        Map<CityNode, Integer> dist = new HashMap<>();
        Set<CityNode> visited = new HashSet<>();

        PriorityQueue<CityNodeWithDist> queue = new PriorityQueue<>();

        queue.add(new CityNodeWithDist(
                source,
                0,
                heuristic.calculateHeuristic(source, destination)));
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
                            heuristic.calculateHeuristic(neighbour, destination)));
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
