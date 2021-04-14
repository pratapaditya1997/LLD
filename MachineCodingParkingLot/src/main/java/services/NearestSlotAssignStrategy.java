package services;

import lombok.AllArgsConstructor;
import models.ParkingLot;
import models.ParkingSlot;
import models.Vehicle;
import repository.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class NearestSlotAssignStrategy implements SlotAssignStrategy {
    private Repository repository;

    @Override
    public ParkingSlot assignSlot(ParkingLot parkingLot, Vehicle vehicle, Integer x, Integer y) {
        Double requiredSpace = vehicle.getSpaceRequired();

        List<ParkingSlot> availableSlots = parkingLot.getParkingSlots()
                .stream()
                .filter(parkingSlot -> parkingSlot.getSpaceAvailable() >= requiredSpace)
                .collect(Collectors.toList());

        Collections.sort(availableSlots, (s1, s2) -> {
            int d1 = calculateDistance(x, y, s1);
            int d2 = calculateDistance(x, y, s2);
            return d1 - d2;
        });
        return availableSlots.get(0);
    }

    private int calculateDistance(Integer x, Integer y, ParkingSlot s1) {
        return Math.abs(x - s1.getX()) + Math.abs(y - s1.getY());
    }


}
