package service.rider;

import model.RideDetail;
import model.Rider;

import java.util.List;

public interface RiderService {
    Rider registerRider(Rider rider);
    List<RideDetail> getRiderHistory(int riderId);
}
