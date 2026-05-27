package designpatterns.strategy;

public interface HeuristicStrategy {
    int calculateHeuristic(CityNode source, CityNode destination);
}
