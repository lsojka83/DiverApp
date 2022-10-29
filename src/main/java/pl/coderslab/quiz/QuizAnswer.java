package pl.coderslab.quiz;

import lombok.Data;
import pl.coderslab.advice.Image;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "quizanswer")
@Table(name = "quizanswer")
@Data
public class QuizAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @OneToOne
    private Image image;
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
