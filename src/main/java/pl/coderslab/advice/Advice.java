package pl.coderslab.advice;

import lombok.Data;
import pl.coderslab.quiz.QuizQuestion;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "advice")
@Data
@Table(name = "advice")
public class Advice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //    @Column(name = "adviceText", columnDefinition="text")
    @Column(name = "name", length = 200)
    @NotBlank
    private String name;

    @Column(name = "adviceText", length = 3000)
    @NotBlank
    private String adviceText;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull
    private Category category;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(name = "quizQuestions")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "advice_quiz_questions")
    private Set<QuizQuestion> quizQuestions;
    @Column(name = "rating")
    Double rating;
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
