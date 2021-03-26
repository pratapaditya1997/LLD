package service.matching;

import model.RideDetail;
import repository.Repository;

public class MatchingServiceImpl implements MatchingService{
    private MatchingStrategy strategy;
    private Repository repository;

    public MatchingServiceImpl(MatchingStrategy strategy, Repository repository) {
        this.strategy = strategy;
        this.repository = repository;
    }

    public void setStrategy(MatchingStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public RideDetail matchRiderToCab(int riderId) {
        int cabId = strategy.execute(riderId);
        RideDetail rideDetail = new RideDetail(riderId, cabId);
        repository.saveRideDetail(rideDetail);
        return rideDetail;
    }
}
