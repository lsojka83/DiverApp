package pl.coderslab.admin;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.advice.*;
import pl.coderslab.model.FileOps;
import pl.coderslab.model.Role;
import pl.coderslab.model.RoleRepository;
import pl.coderslab.model.UserRepository;
import pl.coderslab.quiz.QuizAnswer;
import pl.coderslab.quiz.QuizAnswerRepository;
import pl.coderslab.quiz.QuizQuestion;
import pl.coderslab.quiz.QuizQuestionRepository;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdviceRepository adviceRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ImageRepository imageRepository;
    private final MovieRepository movieRepository;
    private final QuizQuestionRepository quizQuestionRepository;
    private final QuizAnswerRepository quizAnswerRepository;
    private final FileOps fileOps;


    public AdminController(AdviceRepository adviceRepository, CategoryRepository categoryRepository, UserRepository userRepository, RoleRepository roleRepository, ImageRepository imageRepository, MovieRepository movieRepository, QuizQuestionRepository quizQuestionRepository, QuizAnswerRepository quizAnswerRepository, FileOps fileOps) {
        this.adviceRepository = adviceRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.imageRepository = imageRepository;
        this.movieRepository = movieRepository;
        this.quizQuestionRepository = quizQuestionRepository;
        this.quizAnswerRepository = quizAnswerRepository;
        this.fileOps = fileOps;
    }

    @GetMapping("")
    public String index(Model model) {
        checkIfRolesExist();
        model.addAttribute("envs", getEnviromentalVariales());
        model.addAttribute("sysProperties", getSysProperties());
        return "admin-home";
    }

    private Map<String, String> getSysProperties() {
        Map<String, String> props = new HashMap<>();
        for (String k : System.getProperties().stringPropertyNames()) {
            props.put(k, System.clearProperty(k));
        }
        return props;
    }

    //QUIZ ADVICES
    @GetMapping("/advices")
    public String advices(Model model) {
        model.addAttribute("advices", adviceRepository.findAll());
        return "admin-list-advices";
    }


    @GetMapping("/addeditadvice")
    public String addEditAdvice(Model model,
                                @RequestParam (required = false) Long id) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("images", imageRepository.findAll());
        model.addAttribute("movies", movieRepository.findAll());
        model.addAttribute("quizQuestions", quizQuestionRepository.findAll());
        if (id == null) {
            model.addAttribute("advice", new Advice());
        } else
        {
            model.addAttribute("advice", adviceRepository.findById(id).get());
        }
            return "admin-add-edit-advice";
    }

    @PostMapping("/addeditadvice")
    public String addEditAdvice(@Valid Advice advice, BindingResult result, @RequestParam String confirm
    )
    {
        if(confirm.equals("no"))
        {
            return "redirect:/advices";
        }
        if(result.hasErrors())
        {
            return "admin-add-edit-advice";
        }
        adviceRepository.save(advice);
        return "redirect:/admin/advices";
    }

    @GetMapping("/deleteadvices")
    public String deleteAdvices(Long id)
    {
        adviceRepository.delete(adviceRepository.findById(id).get());
        return "redirect:/admin/advices";
    }

    //QUIZ QUESTIONS
    @GetMapping("/quizquestions")
    public String quizQuestions(Model model)
    {
        model.addAttribute("quizquestions", quizQuestionRepository.findAll());
        return "admin-list-quiz-questions";
    }

    @GetMapping("/addeditquizquestion")
    public String addEditQuizQuestion(Model model,
                                @RequestParam (required = false) Long id)
    {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("quizAnswers", quizAnswerRepository.findAll());
        if (id == null) {
            model.addAttribute("quizQuestion", new QuizQuestion());
        } else
        {
            model.addAttribute("quizQuestion", quizQuestionRepository.findById(id).get());
        }
        return "admin-add-edit-quiz-question";
    }


    @PostMapping("/addeditquizquestion")
    public String addEditQuizQuestion(@Valid QuizQuestion quizQuestion, BindingResult result,
                                      @RequestParam String confirm
    )
    {
        if(confirm.equals("no"))
        {
            return "redirect:/admin/quizquestions";
        }
        if(result.hasErrors())
        {
            return "admin-add-edit-quiz-question";
        }
        quizQuestionRepository.save(quizQuestion);
        return "redirect:/admin/quizquestions";
    }
    @GetMapping("/deletequestions")
    public String deleteQuestions(Long id)
    {
        quizQuestionRepository.delete(quizQuestionRepository.findById(id).get());
        return "redirect:/admin/quizquestions";
    }

//QUIZ ANSWERS
    @GetMapping("/quizanswers")
    public String quizAnswers(Model model)
    {
        model.addAttribute("quizAnswers", quizAnswerRepository.findAll());
        return "admin-list-quiz-answers";
    }

    @GetMapping("/addeditquizanswer")
    public String addEditQuizAnswer(Model model,
                                      @RequestParam (required = false) Long id)
    {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("images", imageRepository.findAll());
        if (id == null) {
            model.addAttribute("quizAnswer", new QuizAnswer());
        } else
        {
            model.addAttribute("quizAnswer", quizAnswerRepository.findById(id).get());
        }
        return "admin-add-edit-quiz-answer";
    }

    @PostMapping("/addeditquizanswer")
    public String addEditQuizAnswer(@Valid QuizAnswer quizAnswer, BindingResult result,
                                      @RequestParam String confirm
    )
    {
        if(confirm.equals("no"))
        {
            return "redirect:/admin/quizanswers";
        }
        if(result.hasErrors())
        {
            return "admin-add-edit-quiz-answer";
        }
        quizAnswerRepository.save(quizAnswer);
        return "redirect:/admin/quizanswers";
    }

    @GetMapping("/deletequizanswer")
    public String deleteAnswer(Long id)
    {
        quizAnswerRepository.delete(quizAnswerRepository.findById(id).get());
        return "redirect:/admin/quizanswers";
    }


    //CATEGORIES
    @GetMapping("/categories")
    public String getCategories(Model model) {

        model.addAttribute("categories", categoryRepository.findAll());
        return "admin-list-categories";
    }

    @GetMapping("/addcategory")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "admin-add-edit-category";
    }

    @PostMapping("/addcategory")
    public String addCategory(
            @Valid Category category, BindingResult result, @RequestParam String confirm) {
        if (confirm.equals("no")) {
            return "redirect:/admin/categories";
        }
        if (result.hasErrors()) {
            return "admin-add-edit-category";
        }
        categoryRepository.save(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/deletecategory")
    public String deleteCategory(Long id)
    {
        categoryRepository.delete(categoryRepository.findById(id).get());
        return "redirect:/admin/categories";
    }

    @GetMapping("/images")
    public String getImages(Model model,
                            HttpServletRequest httpRequest) {
//        for(String v : httpRequest.getParameterMap().keySet())
//        {
//            System.out.println("!!!!"+  httpRequest.getParameterMap().get(v));
//        }
        System.out.println("!!!" + httpRequest.getContextPath().toString());
        model.addAttribute("images", imageRepository.findAll());
        model.addAttribute("url", httpRequest.getScheme()
                + "://" + httpRequest.getServerName() + ":" + httpRequest.getServerPort() + "/images");
        model.addAttribute("imagesFolder", fileOps.getImagesFolder());
        return "admin-list-images";
    }

    @GetMapping("/addimage")
    public String addImage(Model model) {
        model.addAttribute("image", new Image());
        return "admin-add-edit-image";
    }

    @PostMapping("/addimage")
    public String addImage(
            @RequestParam MultipartFile multipartFile,
            @RequestParam String name,
            Model model,
            @RequestParam String confirm
//            @Valid Image image, BindingResult result,
    ) {

//                model.addAttribute("imageFolder",imagesFolder);


        if (confirm.equals("no")) {
            return "redirect:/admin/images";
        }
//        if(result.hasErrors()) {
//            return "admin-add-edit-image";
//        }
//        String multipartFileUrl = "";
//        try {
//             multipartFileUrl = multipartFile.getResource().getURL().toString();
//            System.out.printf("!!!!"+multipartFileUrl);
//        }
//        catch (Exception e){
//        };

        String randomFileName = UUID.randomUUID().toString();
        fileOps.saveFile(multipartFile, randomFileName);


        Image image = new Image();
        image.setName(name);
        image.setLink(randomFileName);
        imageRepository.save(image);
        return "redirect:/admin/images";
    }

    @GetMapping("/deleteimage")
    public String deleteImage(@RequestParam Long id) {
        Image image = imageRepository.findById(id).get();
//        System.out.println("!!!"+image.getLink());
        fileOps.deleteFile(image.getLink());
        imageRepository.delete(image);
        return "redirect:/admin/images";
    }


    private void checkIfRolesExist() {
        if (roleRepository.findByName("ROLE_ADMIN") == null) {
            Role role_admin = new Role();
            role_admin.setName("ROLE_ADMIN");
            roleRepository.save(role_admin);
        }
        if (roleRepository.findByName("ROLE_USER") == null) {
            Role role = new Role();
            role.setName("ROLE_USER");
            roleRepository.save(role);
        }
    }

    private Map<String, String> getEnviromentalVariales() {
        return System.getenv();
    }


}
