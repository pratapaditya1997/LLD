package services;

import models.ParkingLot;
import models.ParkingSlot;
import models.Vehicle;

import java.util.List;

public interface ParkingLotService {
    void getParkingSlot(String registrationNumber, Integer entryPoint);
    void exitParkingLot(String registrationNumber);

    List<Vehicle> getVehiclesWithColor(String color);
    List<Vehicle> getCarsWithColor(String color);

    ParkingSlot getParkingSlotForVehicle(String registrationNumber);
}
