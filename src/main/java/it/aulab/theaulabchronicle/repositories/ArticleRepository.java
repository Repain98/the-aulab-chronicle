package it.aulab.theaulabchronicle.repositories;

import it.aulab.theaulabchronicle.models.Article;
import it.aulab.theaulabchronicle.models.Category;
import it.aulab.theaulabchronicle.models.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ArticleRepository extends ListCrudRepository<Article, Long> {
    List<Article> findByCategory(Category category);

    List<Article> findByUser(User user);

    List<Article> findByIsAcceptedTrue();

    List<Article> findByIsAcceptedFalse();

    List<Article> findByIsAcceptedIsNull();
}