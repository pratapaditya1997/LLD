package service.matching;

import model.Cab;

public interface MatchingStrategy {
    int execute(int riderId);
}
