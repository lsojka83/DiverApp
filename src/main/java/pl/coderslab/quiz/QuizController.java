package pl.coderslab.quiz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final QuizQuestionRepository quizRepository;

    public QuizController(QuizQuestionRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @GetMapping("/{id}")
    public QuizQuestion getQuizQuestion (@PathVariable Long id)
    {
        return quizRepository.findById(id).orElse(new QuizQuestion());
    }


}
