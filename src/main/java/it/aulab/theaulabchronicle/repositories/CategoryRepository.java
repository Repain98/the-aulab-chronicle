package it.aulab.theaulabchronicle.repositories;

import it.aulab.theaulabchronicle.models.Category;
import org.springframework.data.repository.ListCrudRepository;

public interface CategoryRepository extends ListCrudRepository<Category, Long> {

}