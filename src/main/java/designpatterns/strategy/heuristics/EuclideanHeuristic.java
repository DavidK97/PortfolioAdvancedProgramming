package designpatterns.strategy.heuristics;

import designpatterns.strategy.CityNode;

public class EuclideanHeuristic implements HeuristicStrategy {

    @Override
    public int calculateHeuristic(CityNode source, CityNode destination) {
        int dRow = source.getRow()    - destination.getRow();
        int dCol = source.getColumn() - destination.getColumn();
        return (int) Math.sqrt(dRow * dRow + dCol * dCol);
    }
}