package pl.coderslab.quiz;

import lombok.Data;
import pl.coderslab.advice.Category;
import pl.coderslab.advice.Image;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity(name = "quizanswer")
@Table(name = "quizanswer")
@Data
public class QuizAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "answerText")
    @NotBlank
    private String answerText;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull
    private Category category;
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
