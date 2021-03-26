package service.rider;

import model.RideDetail;
import model.Rider;
import repository.Repository;

import java.util.List;

public class RiderServiceImpl implements RiderService{
    private Repository repository;

    public RiderServiceImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Rider registerRider(Rider rider) {
        return repository.saveRider(rider);
    }

    @Override
    public List<RideDetail> getRiderHistory(int riderId) {
        return repository.getRiderHistory(riderId);
    }
}
