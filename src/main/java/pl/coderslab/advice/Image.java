package pl.coderslab.advice;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "image")
@Table(name = "image")
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "link")
    private String link;
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
