package service.matching;

import model.Cab;
import model.Location;
import model.Rider;
import repository.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ClosestLocationMatchingStrategy implements MatchingStrategy{
    private Repository repository;

    public ClosestLocationMatchingStrategy(Repository repository) {
        this.repository = repository;
    }

    @Override
    public int execute(int riderId) {
        Rider rider = repository.getRiderDetails(riderId);
        List<Cab> availableCabs = repository.getCabs()
                .stream()
                .filter(c -> c.isAvailable())
                .collect(Collectors.toList());

        Collections.sort(availableCabs, (c1, c2) -> {
            int d1 = calculateDistance(rider.getLocation(), c1.getLocation());
            int d2 = calculateDistance(rider.getLocation(), c2.getLocation());
            return d1 - d2;
        });
        return availableCabs.get(0).getCabId();
    }

    private int calculateDistance(Location l1, Location l2) {
        return Math.abs(l1.getX() - l2.getX()) + Math.abs(l1.getY() - l2.getY());
    }
}
