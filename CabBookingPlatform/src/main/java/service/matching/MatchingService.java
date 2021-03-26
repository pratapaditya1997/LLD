package service.matching;

import model.RideDetail;

public interface MatchingService {
    RideDetail matchRiderToCab(int riderId);
}
