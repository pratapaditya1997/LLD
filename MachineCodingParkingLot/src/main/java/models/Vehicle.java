package models;

import enums.VehicleType;
import lombok.Getter;

@Getter
public class Vehicle {
    private final VehicleType vehicleType;
    private final String registrationNumber;
    private final String color;

    public Vehicle(VehicleType vehicleType, String registrationNumber, String color) {
        this.vehicleType = vehicleType;
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    public Double getSpaceRequired() {
        switch (vehicleType) {
            case CAR:
                return 0.5;
            case BIKE:
                return 0.25;
            case TRUCK:
                return 1.0;
            case BUS:
                return 1.0;
        }
        return 1.0;
    }
}
