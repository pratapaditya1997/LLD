package repository;

import exception.CabNotFoundException;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RepositoryImpl implements Repository {
    private List<RideDetail> rideDetails = new ArrayList<>();
    private List<Rider> riders = new ArrayList<>();
    private List<Cab> cabs = new ArrayList<>();
    private List<Driver> drivers = new ArrayList<>();

    @Override
    public Rider saveRider(Rider rider) {
        int riderId = riders.size();
        rider.setRiderId(riderId);
        riders.add(rider);
        return rider;
    }

    @Override
    public Driver saveDriver(Driver driver) {
        int driverId = drivers.size();
        driver.setDriverId(driverId);
        drivers.add(driver);
        return driver;
    }

    @Override
    public Cab saveCab(Cab cab) {
        int cabId = cabs.size();
        cab.setCabId(cabId);
        cabs.add(cab);
        return cab;
    }

    @Override
    public void saveRideDetail(RideDetail rideDetail) {
        rideDetails.add(rideDetail);
    }

    @Override
    public void updateCabLocation(int cabId, Location location) throws CabNotFoundException {
        Cab cab = cabs.stream().filter(c -> c.getCabId() == cabId).findFirst().orElse(null);
        if (Objects.isNull(cab)) {
            throw new CabNotFoundException("This cab does not exist in the database");
        }
        cab.setLocation(location);
    }

    @Override
    public void toggleCabAvailability(int cabId) throws CabNotFoundException {
        Cab cab = cabs.stream().filter(c -> c.getCabId() == cabId).findFirst().orElse(null);
        if (Objects.isNull(cab)) {
            throw new CabNotFoundException("This cab does not exist in the database");
        }
        cab.setAvailable(!cab.isAvailable());
    }

    @Override
    public Rider getRiderDetails(int riderId) {
        return riders.stream().filter(f -> f.getRiderId() == riderId).findFirst().orElse(null);
    }

    @Override
    public List<RideDetail> getRiderHistory(int riderId) {
        return rideDetails
                .stream()
                .filter(ride -> ride.getRiderId() == riderId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cab> getCabs() {
        return cabs;
    }
}
