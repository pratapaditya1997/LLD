package repository;

import models.ParkingTicket;
import models.Vehicle;

import java.util.List;

public interface Repository {
    Vehicle getVehicleFromRegistrationNumber(String registrationNumber);
    void addParkingTicket(ParkingTicket ticket);
    ParkingTicket getParkingTicket(String RegistrationNumber);
    List<Vehicle> getVehiclesWithColor(String color);
    void addVehicle(Vehicle vehicle);
}
