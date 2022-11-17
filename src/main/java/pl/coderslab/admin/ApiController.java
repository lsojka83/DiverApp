package pl.coderslab.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.advice.*;
import pl.coderslab.model.Role;
import pl.coderslab.model.RoleRepository;

import java.util.List;

@RestController
public class ApiController {

//    private AdviceRepository adviceRepository;
    private final RoleRepository roleRepository;
    private final AdviceService adviceService;
    private final RatingRepository ratingRepository;
    private  final  InternalOperations internalOperations;


    public ApiController(RoleRepository roleRepository, AdviceService adviceService, RatingRepository ratingRepository, InternalOperations internalOperations) {
//        this.adviceRepository = adviceRepository;
        this.roleRepository = roleRepository;
        this.adviceService = adviceService;
        this.ratingRepository = ratingRepository;
        this.internalOperations = internalOperations;
    }

    @GetMapping("/advices")
    List<Advice> getAllAdvices()
    {

        return adviceService.getAllAdvices();
    }

    @GetMapping("/advices/{id}")
        Advice getAdviceById(@PathVariable Long id)
        {

            return adviceService.getAdviceById(id).orElseThrow(() ->
            {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nie znaleziono");

        });


//                return adviceRepository.findById(id).orElseThrow(
//                        ()->{
//                            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nie znaleziono");
//                        }
//                );
        }

    @GetMapping("/roles")
    public List<Role> getRoles()
    {
        return roleRepository.findAll();
    }

    @PostMapping("/addadvice")
    public String addAdvice(@RequestBody Advice advice)
    {

        if(adviceService.addAdvice(advice))
        {
            return "Advice added";
        }
        else {
            return "Advice not added";
        }

    }

    @PutMapping("/editadvice")
    public String editAdvice(@RequestBody Advice advice)
    {

        if(adviceService.updateAdvice(advice))
        {
            return "Advice edited";
        }
        else {
            return "No such advice";
        }
    }

    @PostMapping("/addadvicerating")
    @ResponseStatus
    public ResponseEntity<String> addAdviceRating(@RequestBody RatingClient ratingClient) {
        Rating rating = new Rating();
        rating.setValue(ratingClient.getValue());
        rating.setAdvice(getAdviceById(ratingClient.getAdviceId()));
        try {
            ratingRepository.save(rating);
        }catch (Exception e)
        {
//            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"ZLA WARTOSC");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("ZLA WARTOSC\n");
        }


//        List<Advice> advs = adviceService.getAllAdvices();
//        adviceService.updateAdvice(advice);
        Advice advice = getAdviceById(ratingClient.getAdviceId());
        advice.getReceivedRatings().add(rating);
        adviceService.updateAdvice(advice);

        System.out.println("!!!!"+getAdviceById(ratingClient.getAdviceId()).getReceivedRatings().size());

        internalOperations.incrementRatingsCount(ratingClient.getAdviceId());
        internalOperations.calculateTotalRating(ratingClient.getAdviceId());

//        throw new ResponseStatusException(HttpStatus.CREATED,"CREATED");

        return ResponseEntity.status(HttpStatus.CREATED).body("OK\n");
    }


}
