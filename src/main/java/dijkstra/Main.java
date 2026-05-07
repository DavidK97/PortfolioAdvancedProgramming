package dijkstra;

import java.util.HashMap;
import java.util.Map;

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
    }

    public static void findShortestPath (CityNode start, CityNode destination) {
        Map<CityNode, CityNode> prev = new HashMap<>();
    }
}
