package it.aulab.theaulabchronicle.dtos;

import it.aulab.theaulabchronicle.models.Category;
import it.aulab.theaulabchronicle.models.Image;
import it.aulab.theaulabchronicle.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class ArticleDto {
    private long id;
    private String title;
    private String subtitle;
    private String body;
    private LocalDate publishDate;
    private Boolean isAccepted;
    private User user;
    private Category category;
    private Image image;
}