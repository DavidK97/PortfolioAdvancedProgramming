package designpatterns.strategy;

public class ManhattanHeuristic implements HeuristicStrategy{

    @Override
    public int calculateHeuristic(CityNode source, CityNode destination) {
        return Math.abs(destination.getRow() - source.getRow())
                + Math.abs(destination.getColumn() - source.getColumn());
    }
}
