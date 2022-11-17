package pl.coderslab.admin;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.coderslab.advice.Advice;
import pl.coderslab.advice.AdviceRepository;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class AdviceDBService implements AdviceService {


    private final AdviceRepository adviceRepository;

    public AdviceDBService(AdviceRepository adviceRepository) {
        this.adviceRepository = adviceRepository;
    }

    @Override
    public List<Advice> getAllAdvices() {
        return adviceRepository.findAll();
    }

    @Override
    public Optional<Advice> getAdviceById(Long id) {
        return adviceRepository.findById(id);
    }

    @Override
    public boolean addAdvice(Advice advice) {
        if (!checkIfAdviceExists(advice.getId())) {
            adviceRepository.save(advice);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateAdvice(Advice advice) {
        if (!checkIfAdviceExists(advice.getId())) {
            adviceRepository.save(advice);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkIfAdviceExists(Long id) {
        {
            if (adviceRepository.findById(id).isPresent()) {
                return true;
            } else {
                return false;
            }
        }
    }
}
