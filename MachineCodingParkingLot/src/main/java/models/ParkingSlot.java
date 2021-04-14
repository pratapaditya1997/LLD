package models;

import lombok.Getter;

@Getter
public class ParkingSlot {
    private final Integer x;
    private final Integer y;
    private Double spaceAvailable;

    // checkpoints can be used as entry/exit point
    private boolean checkPoint;

    public ParkingSlot(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        this.spaceAvailable = 1.0;
        this.checkPoint = false;
    }

    public void setCheckPoint(boolean checkPoint) {
        this.checkPoint = checkPoint;
    }

    public void updateStatus(Vehicle vehicle) {
        spaceAvailable -= vehicle.getSpaceRequired();
    }

    public void updateStatusOnExit(Vehicle vehicle) {
        spaceAvailable += vehicle.getSpaceRequired();
    }
}
