package it.aulab.theaulabchronicle.repositories;

import it.aulab.theaulabchronicle.models.CareerRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CareerRequestRepository extends CrudRepository<CareerRequest, Long> {
    List<CareerRequest> findByStatus(String status);

    List<CareerRequest> findAllByOrderByIdDesc();

    @Query(value = "SELECT user_id FROM users_roles", nativeQuery = true)
    List<Long> findAllUserIds();

    @Query(value = "SELECT role_id FROM users_roles WHERE user_id = :id ", nativeQuery = true)
    List<Long> findByUserId(@Param("id") Long id);
}