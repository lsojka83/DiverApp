package pl.coderslab.admin;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.advice.*;
import pl.coderslab.model.Role;
import pl.coderslab.model.RoleRepository;
import pl.coderslab.model.UserRepository;
import pl.coderslab.quiz.QuizAnswerRepository;
import pl.coderslab.quiz.QuizQuestionRepository;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdviceRepository adviceRepository;
    private final CategoryRepository categoryRepository;
    private  final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ImageRepository imageRepository;
    private final MovieRepository movieRepository;
    private final QuizQuestionRepository quizQuestionRepository;
    private final QuizAnswerRepository quizAnswerRepository;

    private final String serverDir = System.getProperty("user.dir");


    public AdminController(AdviceRepository adviceRepository, CategoryRepository categoryRepository, UserRepository userRepository, RoleRepository roleRepository, ImageRepository imageRepository, MovieRepository movieRepository, QuizQuestionRepository quizQuestionRepository, QuizAnswerRepository quizAnswerRepository) {
        this.adviceRepository = adviceRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.imageRepository = imageRepository;
        this.movieRepository = movieRepository;
        this.quizQuestionRepository = quizQuestionRepository;
        this.quizAnswerRepository = quizAnswerRepository;
    }

    @GetMapping("")
    public String index(Model model)
    {
        checkIfRolesExist();
        model.addAttribute("envs",getEnviromentalVariales());
        model.addAttribute("sysProperties",getSysProperties());
        return "admin-home";
    }

    private Map<String, String> getSysProperties() {
        Map<String, String> props = new HashMap<>();
        for(String k : System.getProperties().stringPropertyNames())
        {
            props.put(k,System.clearProperty(k));
        }
        return props;
    }

    @GetMapping("/1")
    public String advice(Model model)
    {
        return "advices";
    }

    @GetMapping("/2")
    public String quizquestions(Model model)
    {
        return "quiz-questions";
    }

    @GetMapping("/categories")
    public String getCategories(Model model)
    {

        model.addAttribute("categories", categoryRepository.findAll());
        return "admin-list-categories";
    }

    @GetMapping("/addcategory")
    public String addCategory(Model model)
    {
        model.addAttribute("category",new Category());
        return "admin-add-edit-category";
    }

    @PostMapping("/addcategory")
    public String addCategory(
            @Valid Category category, BindingResult result, @RequestParam String confirm)
    {
        if(confirm.equals("no"))
        {
            return "redirect:/admin/categories";
        }
        if(result.hasErrors()) {
            return "admin-add-edit-category";
        }
        categoryRepository.save(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/images")
    public String getImages(Model model)
    {
        model.addAttribute("images",imageRepository.findAll());
        return "admin-list-images";
    }

    @GetMapping("/addimage")
    public String addImage(Model model)
    {
        model.addAttribute("image",new Image());
        return "admin-add-edit-image";
    }

    @PostMapping("/addimage")
    public String addImage(
            @RequestParam MultipartFile multipartFile,
            @RequestParam String name
            ,

            Model model
//            @Valid Image image, BindingResult result, @RequestParam String confirm
            )
    {

        String imagesFolder = serverDir+"\\images\\";
        File theDir = new File(imagesFolder);
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        model.addAttribute("imageFolder",imagesFolder);
        String randomFileName = UUID.randomUUID().toString();
        String filePath = imagesFolder + randomFileName+".jpg";

        Path path = Paths.get(filePath);
        File file = new File(filePath);

        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (OutputStream os = new FileOutputStream(file)) {
            os.write(multipartFile.getBytes());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        if(confirm.equals("no"))
//        {
//            return "redirect:/admin/images";
//        }
//        if(result.hasErrors()) {
//            return "admin-add-edit-image";
//        }
        Image image = new Image();
        image.setName(name);
        image.setLink(filePath);
        imageRepository.save(image);
        return "redirect:/admin/images";
    }

    private void checkIfRolesExist()
    {
        if(roleRepository.findByName("ROLE_ADMIN")==null)
        {
            Role role_admin = new Role();
            role_admin.setName("ROLE_ADMIN");
            roleRepository.save(role_admin);
        }
        if(roleRepository.findByName("ROLE_USER")==null)
        {
            Role role = new Role();
            role.setName("ROLE_USER");
            roleRepository.save(role);
        }
    }

    private Map<String, String> getEnviromentalVariales()
    {
     return System.getenv();
    }


}
