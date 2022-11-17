package pl.coderslab.advice;


import org.springframework.stereotype.Component;
import pl.coderslab.admin.AdviceService;
import pl.coderslab.auxiliaries.Averager;

import java.util.List;

@Component
public class InternalOperations {

    private final AdviceService adviceService;
    private final AdviceRepository adviceRepository;
    private final RatingRepository ratingRepository;

    public InternalOperations(AdviceService adviceService, AdviceRepository adviceRepository, RatingRepository ratingRepository) {
        this.adviceService = adviceService;
        this.adviceRepository = adviceRepository;
        this.ratingRepository = ratingRepository;
    }

    public void calculateTotalRating(Long adviceId)
    {
        Advice advice = adviceService.getAdviceById(adviceId).get();
        List<Rating> ratings = advice.getReceivedRatings();
        advice.setRating(ratings.stream().map(Rating::getValue).collect(Averager::new, Averager:: accept, Averager::combine).average());
        adviceRepository.save(advice);
    }

    public void incrementRatingsCount(Long adviceId)
    {
        Advice advice = adviceService.getAdviceById(adviceId).get();
        if(advice.getRatingsCount()==null)
        {
            advice.setRatingsCount(0l);
        }
        advice.setRatingsCount(advice.getRatingsCount()+1);
//        adviceService.updateAdvice(advice);
        adviceRepository.save(advice);

    }

}
