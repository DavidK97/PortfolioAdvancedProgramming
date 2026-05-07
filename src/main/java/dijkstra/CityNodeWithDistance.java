package dijkstra;

public class CityNodeWithDistance implements Comparable <CityNodeWithDistance> {
    CityNode cityNode;
    int distance;

    public CityNodeWithDistance(CityNode cityNode, int distance) {
        this.cityNode = cityNode;
        this.distance = distance;


    }

    @Override
    public int compareTo(CityNodeWithDistance other) {
        return Integer.compare(this.distance, other.distance);
    }
}
