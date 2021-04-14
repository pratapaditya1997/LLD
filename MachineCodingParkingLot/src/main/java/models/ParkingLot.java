package models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ParkingLot {
    private List<ParkingSlot> parkingSlots = new ArrayList<>();
    private final Integer width;
    private final Integer length;

    public ParkingLot(Integer length, Integer width) {
        this.width = width;
        this.length = length;

        for (int i=0; i<length; i++) {
            for (int j=0; j<width; j++) {
                ParkingSlot slot = new ParkingSlot(i, j);
                parkingSlots.add(slot);
            }
        }
        assignCheckpoints();
    }

    public Integer getXForCheckPoint(Integer checkpoint) {
        switch (checkpoint) {
            case 0:
                return 0;
            case 1:
                return 0;
            case 2:
                return length - 1;
            case 3:
                return length - 1;
            default:
                return 0;
        }
    }

    public Integer getYForCheckPoint(Integer checkpoint) {
        switch (checkpoint) {
            case 0:
                return 0;
            case 1:
                return width - 1;
            case 2:
                return length - 1;
            case 3:
                return 0;
            default:
                return 0;
        }
    }

    public void assignCheckpoints() {
        for (ParkingSlot slot: parkingSlots) {
            if (slot.getX() == 0 && slot.getY() == 0 ||
                slot.getX() == 0 && slot.getY() == width - 1 ||
                slot.getX() == length - 1 && slot.getY() == 0 ||
                slot.getX() == length - 1 && slot.getY() == width - 1
            ) {
                slot.setCheckPoint(true);
            }
        }
    }

    public boolean isParkingLotFull() {
        Double spaceAvailable = 0.0;
        for (ParkingSlot slot: parkingSlots) {
            spaceAvailable += slot.getSpaceAvailable();
        }
        if (spaceAvailable == 0.0) return true;
        return false;
    }
}
