package designpatterns.strategy.heuristics;

import designpatterns.strategy.CityNode;

public class ManhattanHeuristic implements HeuristicStrategy {

    @Override
    public int calculateHeuristic(CityNode source, CityNode destination) {
        return Math.abs(destination.getRow() - source.getRow())
                + Math.abs(destination.getColumn() - source.getColumn());
    }
}
