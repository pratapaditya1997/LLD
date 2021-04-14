package repository;

import models.ParkingTicket;
import models.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// rahul.keshri@swiggy.in

public class InMemoryRepository implements Repository{
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<ParkingTicket> parkingTickets = new ArrayList<>();

    @Override
    public Vehicle getVehicleFromRegistrationNumber(String registrationNumber) {
        return vehicles.stream()
                .filter((vehicle -> vehicle.getRegistrationNumber().equalsIgnoreCase(registrationNumber)))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addParkingTicket(ParkingTicket ticket) {
        parkingTickets.add(ticket);
    }

    @Override
    public ParkingTicket getParkingTicket(String registrationNumber) {
        return parkingTickets.stream()
                .filter(ticket -> ticket.getVehicleRegistrationNumber().equalsIgnoreCase(registrationNumber))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Vehicle> getVehiclesWithColor(String color) {
        return vehicles.stream()
                .filter((vehicle -> vehicle.getColor().equalsIgnoreCase(color)))
                .collect(Collectors.toList());
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }
}
