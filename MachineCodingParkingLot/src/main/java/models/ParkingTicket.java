package models;

import enums.ParkingTicketStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingTicket {
    private final String vehicleRegistrationNumber;
    private final long entryTime;
    private ParkingTicketStatus ticketStatus;
    private ParkingSlot slot;

    public ParkingTicket(String vehicleRegistrationNumber, long entryTime, ParkingSlot slot) {
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
        this.entryTime = entryTime;
        this.ticketStatus = ParkingTicketStatus.OPEN;
        this.slot = slot;
    }
}
