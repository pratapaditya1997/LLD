package services;

import enums.ParkingTicketStatus;
import enums.VehicleType;
import lombok.AllArgsConstructor;
import models.ParkingLot;
import models.ParkingSlot;
import models.ParkingTicket;
import models.Vehicle;
import repository.Repository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ParkingLotServiceImpl implements ParkingLotService {
    private Repository repository;
    private ParkingLot parkingLot;
    private SlotAssignStrategy assignStrategy;

    @Override
    public void getParkingSlot(String registrationNumber, Integer entryPoint) {
        if (parkingLot.isParkingLotFull()) {
            System.out.println("Sorry, parking lot is full");
        }
        Vehicle vehicle = repository.getVehicleFromRegistrationNumber(registrationNumber);
        Integer x = parkingLot.getXForCheckPoint(entryPoint);
        Integer y = parkingLot.getYForCheckPoint(entryPoint);

        ParkingSlot slot = assignStrategy.assignSlot(parkingLot, vehicle, x, y);
        slot.updateStatus(vehicle);

        System.out.println("Vehicle is parked at Slot: " + slot.getX() + " " + slot.getY());

        long entryTime = System.currentTimeMillis();
        ParkingTicket parkingTicket = new ParkingTicket(registrationNumber, entryTime, slot);
        repository.addParkingTicket(parkingTicket);
        return;
    }

    @Override
    public void exitParkingLot(String registrationNumber) {
        ParkingTicket ticket = repository.getParkingTicket(registrationNumber);
        Vehicle vehicle = repository.getVehicleFromRegistrationNumber(registrationNumber);
        ParkingSlot slot = ticket.getSlot();
        slot.updateStatusOnExit(vehicle);
        // payment flow - if payment is done , then update the ticket status
        ticket.setTicketStatus(ParkingTicketStatus.CLOSED);
    }

    @Override
    public List<Vehicle> getVehiclesWithColor(String color) {
        return repository.getVehiclesWithColor(color);
    }

    @Override
    public List<Vehicle> getCarsWithColor(String color) {
        return repository.getVehiclesWithColor(color)
                .stream()
                .filter(vehicle -> vehicle.getVehicleType() == VehicleType.CAR)
                .collect(Collectors.toList());
    }

    @Override
    public ParkingSlot getParkingSlotForVehicle(String registrationNumber) {
        return null;
    }
}
