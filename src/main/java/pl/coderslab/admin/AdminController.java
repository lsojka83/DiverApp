package pl.coderslab.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.advice.*;
import pl.coderslab.model.*;
import pl.coderslab.quiz.QuizAnswer;
import pl.coderslab.quiz.QuizAnswerRepository;
import pl.coderslab.quiz.QuizQuestion;
import pl.coderslab.quiz.QuizQuestionRepository;
import pl.coderslab.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.*;
import java.util.stream.Collectors;

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

    private final UserService userService;


    public AdminController(AdviceRepository adviceRepository, CategoryRepository categoryRepository, UserRepository userRepository, RoleRepository roleRepository, ImageRepository imageRepository, MovieRepository movieRepository, QuizQuestionRepository quizQuestionRepository, QuizAnswerRepository quizAnswerRepository, FileOps fileOps, UserService userService) {
        this.adviceRepository = adviceRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.imageRepository = imageRepository;
        this.movieRepository = movieRepository;
        this.quizQuestionRepository = quizQuestionRepository;
        this.quizAnswerRepository = quizAnswerRepository;
        this.fileOps = fileOps;
        this.userService = userService;
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

    //ADVICES
    @GetMapping("/advices")
    public String advices(Model model) {
        model.addAttribute("advices", adviceRepository.findAll());
        return "admin-list-advices";
    }


    @GetMapping("/addeditadvice")
    public String addEditAdvice(Model model,
                                @RequestParam(required = false) Long id) {
        List<Category> categories = categoryRepository.findAll();
//        categories.add(null);
        model.addAttribute("categories", categories);
        model.addAttribute("images", imageRepository.findAll());
        model.addAttribute("movies", movieRepository.findAll());
        model.addAttribute("quizQuestions", quizQuestionRepository.findAll());
        if (id == null) {
            model.addAttribute("advice", new Advice());
        } else {
            model.addAttribute("advice", adviceRepository.findById(id).get());
        }
        return "admin-add-edit-advice";
    }

    @PostMapping("/addeditadvice")
    public String addEditAdvice(@Valid Advice advice, BindingResult result, @RequestParam String confirm
    ) {
        if (confirm.equals("no")) {
            return "redirect:/admin/advices";
        }
        if (result.hasErrors()) {
            return "admin-add-edit-advice";
        }
        adviceRepository.save(advice);
        return "redirect:/admin/advices";
    }

    @GetMapping("/deleteadvice")
    public String deleteAdvices(Long id) {
        adviceRepository.delete(adviceRepository.findById(id).get());
        return "redirect:/admin/advices";
    }

    //QUIZ QUESTIONS
    @GetMapping("/quizquestions")
    public String quizQuestions(Model model) {
        model.addAttribute("quizquestions", quizQuestionRepository.findAll());
        return "admin-list-quiz-questions";
    }

    @GetMapping("/addeditquizquestion")
    public String addEditQuizQuestion(Model model,
                                      @RequestParam(required = false) Long id) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("quizAnswers", quizAnswerRepository.findAll());
        if (id == null) {
            model.addAttribute("quizQuestion", new QuizQuestion());
        } else {
            model.addAttribute("quizQuestion", quizQuestionRepository.findById(id).get());
        }
        return "admin-add-edit-quiz-question";
    }


    @PostMapping("/addeditquizquestion")
    public String addEditQuizQuestion(@Valid QuizQuestion quizQuestion, BindingResult result,
                                      @RequestParam String confirm
    ) {
        if (confirm.equals("no")) {
            return "redirect:/admin/quizquestions";
        }
        if (result.hasErrors()) {
            return "admin-add-edit-quiz-question";
        }
        quizQuestionRepository.save(quizQuestion);
        return "redirect:/admin/quizquestions";
    }

    @GetMapping("/deletequizquestion")
    public String deleteQuestion(Long id) {
        quizQuestionRepository.delete(quizQuestionRepository.findById(id).get());
        return "redirect:/admin/quizquestions";
    }

    //QUIZ ANSWERS
    @GetMapping("/quizanswers")
    public String quizAnswers(Model model) {
        model.addAttribute("quizAnswers", quizAnswerRepository.findAll());
        return "admin-list-quiz-answers";
    }

    @GetMapping("/addeditquizanswer")
    public String addEditQuizAnswer(Model model,
                                    @RequestParam(required = false) Long id) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("images", imageRepository.findAll());
        if (id == null) {
            model.addAttribute("quizAnswer", new QuizAnswer());
        } else {
            model.addAttribute("quizAnswer", quizAnswerRepository.findById(id).get());
        }
        return "admin-add-edit-quiz-answer";
    }

    @PostMapping("/addeditquizanswer")
    public String addEditQuizAnswer(@Valid QuizAnswer quizAnswer, BindingResult result,
                                    @RequestParam String confirm
    ) {
        if (confirm.equals("no")) {
            return "redirect:/admin/quizanswers";
        }
        if (result.hasErrors()) {
            return "admin-add-edit-quiz-answer";
        }
        quizAnswerRepository.save(quizAnswer);
        return "redirect:/admin/quizanswers";
    }

    @GetMapping("/deletequizanswer")
    public String deleteAnswer(Long id, Model model) {
        QuizAnswer quizAnswer = quizAnswerRepository.findById(id).get();

//        adviceRepository.findAll().stream().filter(a -> a.getImage()!=null)
//                .filter(a -> a.getImage().getId().equals(id))
//                .forEach(a-> a.setImage(null));

//        quizQuestionRepository.findAll().stream()
//                .filter(q ->q.getCorrectAnswer()!=null)
//                .filter(q ->q.getCorrectAnswer().getId().equals(id))
//                .forEach(q->q.setCorrectAnswer(null));
//
//        quizQuestionRepository.findAll().stream()
//                .filter(q ->q.getFirstIncorrectAnswer()!=null)
//                .filter(q ->q.getFirstIncorrectAnswer().getId().equals(id))
//                .forEach(q->q.setFirstIncorrectAnswer(null));
//
//        quizQuestionRepository.findAll().stream()
//                .filter(q ->q.getSecondIncorrectAnswer()!=null)
//                .filter(q ->q.getSecondIncorrectAnswer().getId().equals(id))
//                .forEach(q->q.setSecondIncorrectAnswer(null));

        List<QuizQuestion> allQuestions = quizQuestionRepository.findAll();

        if (allQuestions.stream()
                .filter(q -> q.getCorrectAnswer() != null)
                .anyMatch(q -> q.getCorrectAnswer().getId().equals(id))
                ||
                allQuestions.stream()
                        .filter(q -> q.getFirstIncorrectAnswer() != null)
                        .anyMatch(q -> q.getFirstIncorrectAnswer().getId().equals(id))
                ||
                allQuestions.stream()
                        .filter(q -> q.getSecondIncorrectAnswer() != null)
                        .anyMatch(q -> q.getSecondIncorrectAnswer().getId().equals(id))
        ) {
            model.addAttribute("answerUsed", "yes");
            model.addAttribute("quizAnswers", quizAnswerRepository.findAll());
            return "admin-list-quiz-answers";
        }


        quizAnswerRepository.delete(quizAnswer);
        return "redirect:/admin/quizanswers";
    }


    //CATEGORIES
    @GetMapping("/categories")
    public String getCategories(Model model) {

        model.addAttribute("categories", categoryRepository.findAll());
        return "admin-list-categories";
    }

    @GetMapping("/addeditcategory")
    public String addCategory(Model model,
                              @RequestParam(required = false) Long id) {
        if (!(id == null)) {
            model.addAttribute("category", categoryRepository.findById(id).get());
        } else {
            model.addAttribute("category", new Category());
        }
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
    public String deleteCategory(Long id, Model model) {
        List<Advice> allAdvices = adviceRepository.findAll();
        List<QuizQuestion> allQuestions = quizQuestionRepository.findAll();
        List<QuizAnswer> quizAnswers = quizAnswerRepository.findAll();

        if (allAdvices.stream()
                .filter(q -> q.getCategory() != null)
                .anyMatch(q -> q.getCategory().getId().equals(id))
                ||
                allQuestions.stream()
                        .filter(q -> q.getCategory() != null)
                        .anyMatch(q -> q.getCategory().getId().equals(id))
                ||
                quizAnswers.stream()
                        .filter(q -> q.getCategory() != null)
                        .anyMatch(q -> q.getCategory().getId().equals(id))
        ) {
            model.addAttribute("answerUsed", "yes");
            model.addAttribute("categories", categoryRepository.findAll());
            return "admin-list-categories";
        }


        categoryRepository.delete(categoryRepository.findById(id).get());
        return "redirect:/admin/categories";
    }

    //IMAGES
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
//        model.addAttribute("url", httpRequest.getScheme()
//                + "://" + httpRequest.getServerName() + ":" + httpRequest.getServerPort() + "/image?uuid=");
        model.addAttribute("imagesFolder", fileOps.getImagesFolder());
        return "admin-list-images";
    }

    @GetMapping("/addeditimage")
    public String addImage(Model model,
                           @RequestParam(required = false) Long id) {
        if (id == null) {
            model.addAttribute("image", new Image());
        } else {
            model.addAttribute("image", imageRepository.findById(id).get());
        }
        return "admin-add-edit-image";
    }

    @PostMapping("/addeditimage")
    public String addImage(
            @RequestParam(required = false) MultipartFile multipartFile,
            @Valid Image image, BindingResult result,
            @RequestParam String confirm
    ) {
        if (confirm.equals("no")) {
            return "redirect:/admin/images";
        }
        if (result.hasErrors()) {
            return "admin-add-edit-image";
        }

        if (!multipartFile.isEmpty()) {
            String randomFileName = UUID.randomUUID().toString();
            fileOps.saveFile(multipartFile, randomFileName);
            image.setLink(randomFileName);
        }
        imageRepository.save(image);
        return "redirect:/admin/images";
    }

    @GetMapping("/deleteimage")
    public String deleteImage(@RequestParam Long id) {
        Image image = imageRepository.findById(id).get();
        quizAnswerRepository.findAll().stream().filter(a -> a.getImage() != null)
                .filter(a -> a.getImage().getId().equals(id))
                .forEach(a -> a.setImage(null));
        adviceRepository.findAll().stream().filter(a -> a.getImage() != null)
                .filter(a -> a.getImage().getId().equals(id))
                .forEach(a -> a.setImage(null));

        imageRepository.delete(image);
        fileOps.deleteFile(image.getLink());
        return "redirect:/admin/images";
    }

    //ADMINS

    @GetMapping("/admins")
    public String admins(Model model) {

        model.addAttribute("users",
                userRepository.findAll().stream()
                        .filter(user -> user.getRoles()
                                .stream()
                                .anyMatch(r -> r.getName().equals("ROLE_ADMIN"))
                        ).collect(Collectors.toList())
        );
        model.addAttribute("currentUserId", 0);
        return "admin-list-admins";
    }

    @GetMapping("/addeditadmin")
    public String addEditAdmin(Model model,
                               @RequestParam(required = false) Long id) {
        if (id == null) {
            model.addAttribute("user", new User());
            model.addAttribute("newAdmin","yes");
        } else {
            model.addAttribute("user", userRepository.findById(id).get());
        }
        return "admin-add-edit-admin";
    }

    @PostMapping("/addeditadmin")
    public String addEditAdmin(
            @Valid User user, BindingResult result, @RequestParam String confirm, @RequestParam String newAdmin) {
        if (confirm.equals("no")) {
            return "redirect:/admin/admins";
        }
        if (result.hasErrors()) {
            return "admin-add-edit-admin";
        }

        if(newAdmin.equals("yes"))
        {
            userService.saveAdmin(user);
        }
        else {
            userService.saveAdmin(user);
        }
        return "redirect:/admin/admins";
    }


    @GetMapping("/deleteadmin")
    public String deleteAdmin(@RequestParam Long id) {
        userRepository.delete(userRepository.findById(id).get());
        return "redirect:/admin/admins";
    }

    //USERS
    @GetMapping("/users")
    public String usersList(Model model) {
//        model.addAttribute("users",
//                userRepository.findAll().stream()
//                        .filter(user -> user.getRoles()
//                                .stream()
//                                .anyMatch(r -> r.getName().equals("ROLE_USER"))
//                        ).collect(Collectors.toList())
//        );

        //Load all existing users different that admins
                model.addAttribute("users",
                userRepository.findAll().stream()
                        .filter(user -> user.getRoles()
                                .stream()
                                .anyMatch(r -> !r.getName().equals("ROLE_ADMIN"))
                        ).collect(Collectors.toList())
        );
//        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("currentUserId", 0);
        return "admin-list-users";
    }

    @GetMapping("/addedituser")
    public String addEditUser(Model model,
                              @RequestParam(required = false) Long id) {
        if (id == null) {
            model.addAttribute("user", new User());
        } else {
            model.addAttribute("user", userRepository.findById(id).get());
        }
        return "admin-add-edit-user";
    }

    @PostMapping("/addedituser")
    public String addEditUser(
            @Valid User user, BindingResult result, @RequestParam String confirm,
            @RequestParam String password,
            @RequestParam String password2,
            Model model
    ) {

        if (confirm.equals("no")) {
            return "redirect:/admin/users";
        }
        boolean updatePassword = false;
        if (!password.isEmpty() || !password2.isEmpty())
        {
            if (!password.equals(password2))
            {
                model.addAttribute("invalidPassword", Messages.PASSWORD_ARE_NOT_EQUAL);
            } else {
                updatePassword = true;
            }
            if (model.getAttribute("invalidPassword") != null) {
                return "admin-add-edit-user";
            }
        }
        if (result.hasErrors()) {
            return "admin-add-edit-user";
        }

        if(user.getId()==null)
        {
            userService.saveUser(user);
            return "redirect:/admin/users";
        }
        if(!updatePassword) {
            user.setPassword(userService.findById(user.getId()).getPassword());
        }
        userService.updateUser(user, updatePassword);
        return "redirect:/admin/users";
    }

    @GetMapping("/deleteuser")
    public String deleteUser(Long id) {
        userRepository.delete(userRepository.findById(id).get());
        return "redirect:/admin/users";
    }

    //ROLE
    @GetMapping("/roles")
    public String roles(Model model) {
//        model.addAttribute("roles", roleRepository.findAll());
        return "admin-list-roles";
    }

    @GetMapping("/addeditrole")
    public String addEditRole(Model model,
                              @RequestParam(required = false) Long id) {
        if (id == null) {
            model.addAttribute("role", new Role());
        } else {
            model.addAttribute("role", roleRepository.findById(id).get());
        }
        return "admin-add-edit-role";
    }
    @PostMapping("/addeditrole")
    public String addEditRole(
            @Valid Role role, BindingResult result, @RequestParam String confirm) {
        if (confirm.equals("no")) {
            return "redirect:/admin/roles";
        }
        if (result.hasErrors()) {
            return "admin-add-edit-role";
        }
        roleRepository.save(role);
        return "redirect:/admin/roles";
    }

    @GetMapping("/deleterole")
    public String deleteRole(Long id, Model model) {

        if (!userRepository.findAll().stream()
                .filter(u -> !u.getRoles().isEmpty())
                .filter(user -> user.getRoles()
                        .stream()
                        .anyMatch(r -> r.getId().equals(id))
                ).collect(Collectors.toList()).isEmpty()
        )
        {
            model.addAttribute("answerUsed", "yes");
            return "admin-list-roles";
        }
        roleRepository.delete(roleRepository.findById(id).get());
        return "redirect:/admin/roles";
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

    @ModelAttribute("roles")
    public Collection<Role> getRoles()
    {
        List<Role> availableRoles = roleRepository.findAll();
        return availableRoles;
    }

    @ModelAttribute("rolesNoAdmin")
    public Collection<Role> getRolesNoAdmin()
    {
        List<Role> availableRolesNoAdmin = roleRepository.findAll().stream()
                .filter(r->!r.getName().equals("ROLE_ADMIN")).collect(Collectors.toList());
        return availableRolesNoAdmin;
    }


}
