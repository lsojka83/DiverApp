package pl.coderslab.model;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;
import pl.coderslab.advice.Advice;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

@Entity(name = "user")
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Email
    @NotBlank
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    private String password;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    private int enabled;

    private String uuid;

    private int active;

    private int sentResetRequest;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_last_viewed_advices")
    private List<Advice> lastViewedAdvices;

    private Long pointsCount;

    @Column(name = "createdOn")
    private LocalDateTime createdOn;
    @Column(name = "lastModifiedOn")
    private LocalDateTime lastModifiedOn;

    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    @PreUpdate
    public void preUpdate() {
        lastModifiedOn = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

}