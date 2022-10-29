package pl.coderslab.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/advice")
public class AdviceController {

    private final AdviceRepository adviceRepository;

    public AdviceController(AdviceRepository adviceRepository) {
        this.adviceRepository = adviceRepository;
    }

    @GetMapping("")
    public Advice getAdvise()
    {
//        return adviceRepository.findAll().get(0);
        return adviceRepository.findById(adviceRepository.count()).orElse(null);
    }

    @GetMapping("/list")
    private List<Advice> getAdvices()
    {
        return adviceRepository.findAll();
    }


}
