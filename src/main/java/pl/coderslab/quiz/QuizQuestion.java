package pl.coderslab.quiz;

import lombok.Data;
import pl.coderslab.advice.Image;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "quizquestion")
@Data
@Table(name = "quizquestion")
public class QuizQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "qestionText")
    private String qestionText;

    @OneToOne
    private QuizAnswer correctAnswer;

    @OneToOne
    private QuizAnswer firstIncorrectAnswer;

    @OneToOne
    private QuizAnswer secondIncorrectAnswer;
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
