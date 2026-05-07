package dijkstra;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        CityNode helsingør = new CityNode("Helsingør");
        CityNode snekkersten = new CityNode("Snekkersten");
        CityNode espergærde = new CityNode("Espergærde");
        CityNode humlebæk = new CityNode("Humlebæk");
        CityNode hillerød = new CityNode("Hillerød");
        CityNode fredensborg = new CityNode("Fredensborg");
        CityNode gilleleje = new CityNode("Gilleleje");
        CityNode helsinge = new CityNode("Helsinge");

        helsingør.addNeighbour(snekkersten, 5);
        helsingør.addNeighbour(espergærde, 10);

        espergærde.addNeighbour(humlebæk, 3);
        humlebæk.addNeighbour(fredensborg, 2);

        fredensborg.addNeighbour(helsinge, 1);

        snekkersten.addNeighbour(hillerød, 7);
        snekkersten.addNeighbour(gilleleje, 5);

        hillerød.addNeighbour(helsinge, 3);

        gilleleje.addNeighbour(hillerød, 1);

        findShortestPath(helsingør, helsinge);
    }

    public static void findShortestPath(CityNode start, CityNode destination) {
        Map<CityNode, CityNode> prev = new HashMap<>();

        Map<CityNode, Integer> distance = new HashMap<>();

        Set<CityNode> visited = new HashSet<>();

        PriorityQueue<CityNodeWithDistance> queue = new PriorityQueue<>();

        queue.add(new CityNodeWithDistance(start, 0));
        distance.put(start, 0);

        while (!queue.isEmpty()) {
            CityNodeWithDistance current = queue.poll();


            if (current.cityNode.equals(destination)) break;


            if (visited.contains(current.cityNode)) continue;


            visited.add(current.cityNode);


            for (Map.Entry<CityNode, Integer> entry : current.cityNode.getNeighbours().entrySet()) {
                CityNode next = entry.getKey();
                int weight = entry.getValue();


                if (visited.contains(next)) continue;


                int newDist = current.distance + weight;


                if (newDist < distance.getOrDefault(next, Integer.MAX_VALUE)) {
                    distance.put(next, newDist);
                    prev.put(next, current.cityNode);

                    queue.add(new CityNodeWithDistance(next, newDist));
                }
            }

            List<String> path = new ArrayList<>();
            CityNode step = destination;
            while (step != null) {
                path.add(0, step.getName());
                step = prev.get(step);
            }

            System.out.println("Korteste vej: " + path);
            System.out.println("Samlet dist: " + distance.get(destination));
        }
    }
}