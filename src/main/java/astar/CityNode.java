package astar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CityNode {
    private int row;
    private int column;

    private List<CityNode> neighbours = new ArrayList<>();

    public CityNode(int row, int column) {
        this.row = row;
        this.column = column;
    }


    public void addNeighbour (CityNode cityNode) {
        neighbours.add(cityNode);
    }

    public int getRow() {
        return row;
    }

    public List<CityNode> getNeighbours() {
        return neighbours;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CityNode cityNode = (CityNode) o;
        return row == cityNode.row && column == cityNode.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
