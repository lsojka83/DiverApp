package pl.coderslab.quiz;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizQuestionRepository extends JpaRepository<QuizQuestion,Long> {
}
