package pl.coderslab.advice;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "category")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "createdOn")
    private LocalDateTime createdOn;
    @Column(name = "lastModifiedOn")
    private LocalDateTime lastModifiedOn;

    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastModifiedOn = LocalDateTime.now();
    }


}
