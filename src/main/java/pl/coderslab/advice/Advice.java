package pl.coderslab.advice;

import lombok.Data;
import pl.coderslab.quiz.QuizQuestion;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
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
    @NotNull (message = "Należy zaznaczyć przynajmniej jedną kategorię!")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(name = "quizQuestions")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_questions_id")
    private Set<QuizQuestion> quizQuestions;

    @Column(name = "rating")
//    @Min(value = 1, message = "Musi być wieksze niż 1")
//    @Max(value = 5, message = "Musi byc mniejsze niż 5")
    private Double rating;

    @Column(name = "ratings_count")
    private Long ratingsCount;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "advice")
//    @JoinColumn(name = "advice_id")
    private List<Rating> receivedRatings;

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
