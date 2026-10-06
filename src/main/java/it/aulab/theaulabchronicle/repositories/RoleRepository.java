package it.aulab.theaulabchronicle.repositories;

import it.aulab.theaulabchronicle.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}