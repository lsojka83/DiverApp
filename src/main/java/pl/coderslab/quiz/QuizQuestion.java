package pl.coderslab.quiz;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.advice.Category;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Entity(name = "quizquestion")
@Data
@Table(name = "quizquestion")
public class QuizQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "questionText")
    @NotBlank
    private String questionText;

    @ManyToOne
    @NotNull
    private Category category;

    @OneToOne
    @NotNull
    private QuizAnswer correctAnswer;

    @OneToOne
    @NotNull
    private QuizAnswer firstIncorrectAnswer;

    @OneToOne
    @NotNull
    private QuizAnswer secondIncorrectAnswer;

//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createdOn")
    private LocalDateTime createdOn;
    @Column(name = "lastModifiedOn")
    private LocalDateTime lastModifiedOn;

    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
//        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastModifiedOn = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

}
