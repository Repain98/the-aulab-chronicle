package it.aulab.theaulabchronicle.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "career_request")
public class CareerRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String body;

    @Column(nullable = false)
    private String status = "NEW";

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public String getStatusLabel() {
        return switch (status) {
            case "NEW" -> "Nuova";
            case "VIEWED" -> "Visionata";
            case "ACCEPTED" -> "Accettata";
            case "REJECTED" -> "Rifiutata";
            default -> status;
        };
    }
}