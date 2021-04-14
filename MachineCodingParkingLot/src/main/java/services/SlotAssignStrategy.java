package services;

import models.ParkingLot;
import models.ParkingSlot;
import models.Vehicle;

public interface SlotAssignStrategy {
    ParkingSlot assignSlot(ParkingLot parkingLot, Vehicle vehicle, Integer x, Integer y);
}
