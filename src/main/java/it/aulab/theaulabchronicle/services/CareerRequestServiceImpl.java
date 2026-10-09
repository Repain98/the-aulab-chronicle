package it.aulab.theaulabchronicle.services;

import it.aulab.theaulabchronicle.models.CareerRequest;
import it.aulab.theaulabchronicle.models.Role;
import it.aulab.theaulabchronicle.models.User;
import it.aulab.theaulabchronicle.repositories.CareerRequestRepository;
import it.aulab.theaulabchronicle.repositories.RoleRepository;
import it.aulab.theaulabchronicle.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CareerRequestServiceImpl implements CareerRequestService {

    @Autowired
    private CareerRequestRepository careerRequestRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public boolean isRoleAlreadyAssigned(User user, CareerRequest careerRequest) {
        List<Long> allUserIds = careerRequestRepository.findAllUserIds();

        if (!allUserIds.contains(user.getId())) {
            return false;
        }

        List<Long> requests = careerRequestRepository.findByUserId(user.getId());

        return requests.stream().anyMatch(roleId -> roleId.equals(careerRequest.getRole().getId()));
    }

    public void save(CareerRequest careerRequest, User user) {
        careerRequest.setUser(user);
        careerRequest.setStatus("NEW");
        careerRequestRepository.save(careerRequest);

        emailService.sendSimpleEmail("admin1@aulab.com", "Richiesta per ruolo: " + careerRequest.getRole().getName(), "C'è una nuova richiesta di collaborazione da parte di " + user.getUsername());
    }

    @Override
    public void careerAccept(Long requestId) {
        CareerRequest request = careerRequestRepository.findById(requestId).get();

        User user = request.getUser();
        Role role = request.getRole();

        List<Role> rolesUser = user.getRoles();
        Role newRole = roleRepository.findByName(role.getName());
        rolesUser.add(newRole);

        user.setRoles(rolesUser);
        userRepository.save(user);
        request.setStatus("ACCEPTED");
        careerRequestRepository.save(request);

        emailService.sendSimpleEmail(user.getEmail(), "Ruolo abilitato", "Ciao, la tua richiesta di collaborazione è stata accettata dalla nostra amministrazione");
    }

    @Override
    public void careerReject(Long requestId) {
        CareerRequest request = careerRequestRepository.findById(requestId).get();

        request.setStatus("REJECTED");
        careerRequestRepository.save(request);

        emailService.sendSimpleEmail(request.getUser().getEmail(), "Richiesta rifiutata", "Ciao, la tua richiesta di collaborazione è stata rifiutata dalla nostra amministrazione");
    }

    @Override
    public void markAsViewed(Long requestId) {
        CareerRequest request = careerRequestRepository.findById(requestId).get();

        if (request.getStatus().equals("NEW")) {
            request.setStatus("VIEWED");
            careerRequestRepository.save(request);
        }
    }

    @Override
    public CareerRequest find(Long id) {
        return careerRequestRepository.findById(id).get();
    }
}