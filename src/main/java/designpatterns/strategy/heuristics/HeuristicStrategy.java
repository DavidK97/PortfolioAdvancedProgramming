package designpatterns.strategy.heuristics;

import designpatterns.strategy.CityNode;

public interface HeuristicStrategy {
    int calculateHeuristic(CityNode source, CityNode destination);
}
