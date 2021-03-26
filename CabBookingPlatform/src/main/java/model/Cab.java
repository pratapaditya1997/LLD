package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Cab {
    private int cabId;
    private int driverId;
    private Location location;
    private boolean available;

    public Cab(int driverId, Location location, boolean available) {
        this.driverId = driverId;
        this.location = location;
        this.available = available;
    }
}
