package it.aulab.theaulabchronicle.services;

import it.aulab.theaulabchronicle.models.CareerRequest;
import it.aulab.theaulabchronicle.models.User;

public interface CareerRequestService {
    boolean isRoleAlreadyAssigned(User user, CareerRequest careerRequest);

    void save(CareerRequest careerRequest, User user);

    void careerAccept(Long requestId);

    void careerReject(Long requestId);

    void markAsViewed(Long requestId);

    CareerRequest find(Long id);
}