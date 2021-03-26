package repository;

import exception.CabNotFoundException;
import model.*;

import java.util.List;

public interface Repository {
    Rider saveRider(Rider rider);
    Driver saveDriver(Driver driver);
    Cab saveCab(Cab cab);
    void saveRideDetail(RideDetail rideDetail);

    void updateCabLocation(int cabId, Location location) throws CabNotFoundException;
    void toggleCabAvailability(int cabId) throws CabNotFoundException;

    Rider getRiderDetails(int riderId);
    List<RideDetail> getRiderHistory(int riderId);
    List<Cab> getCabs();
}
