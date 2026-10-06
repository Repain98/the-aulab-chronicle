package it.aulab.theaulabchronicle.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    @NotEmpty(message = "Il nome non deve essere vuoto")
    @Size(max = 50)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Article> articles = new ArrayList<Article>();
}