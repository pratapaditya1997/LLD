package service.cab;

import exception.CabNotFoundException;
import model.Cab;
import model.Driver;
import model.Location;

public interface CabService {
    Driver registerDriver(Driver driver);
    Cab registerCab(Cab cab);
    void updateCabLocation(int cabId, Location location) throws CabNotFoundException;
    void toggleCabAvailability(int cabId) throws CabNotFoundException;
}
