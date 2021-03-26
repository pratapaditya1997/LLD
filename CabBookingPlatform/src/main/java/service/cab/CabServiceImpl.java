package service.cab;

import exception.CabNotFoundException;
import model.Cab;
import model.Driver;
import model.Location;
import repository.Repository;

public class CabServiceImpl implements CabService {
    private Repository repository;

    public CabServiceImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Driver registerDriver(Driver driver) {
        return repository.saveDriver(driver);
    }

    @Override
    public Cab registerCab(Cab cab) {
        return repository.saveCab(cab);
    }

    @Override
    public void updateCabLocation(int cabId, Location location) throws CabNotFoundException {
        repository.updateCabLocation(cabId, location);
    }

    @Override
    public void toggleCabAvailability(int cabId) throws CabNotFoundException {
        repository.toggleCabAvailability(cabId);
    }
}
