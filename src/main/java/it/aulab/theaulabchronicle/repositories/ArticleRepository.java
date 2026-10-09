package it.aulab.theaulabchronicle.repositories;

import it.aulab.theaulabchronicle.models.Article;
import it.aulab.theaulabchronicle.models.Category;
import it.aulab.theaulabchronicle.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends ListCrudRepository<Article, Long> {
    List<Article> findByCategory(Category category);

    List<Article> findByUser(User user);

    List<Article> findByIsAcceptedTrue();

    List<Article> findByIsAcceptedFalse();

    List<Article> findByIsAcceptedIsNull();

    @Query("SELECT a FROM Article a WHERE " + "LOWER(a.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " + "LOWER(a.subtitle) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " + "LOWER(a.body) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " + "LOWER(a.user.username) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " + "LOWER(a.user.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " + "LOWER(a.category.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Article> search(@Param("searchTerm") String searchTerm);
}