package pl.coderslab.admin;

import pl.coderslab.advice.Advice;

import java.util.List;
import java.util.Optional;

public interface AdviceService {

    List<Advice> getAllAdvices();

    Optional<Advice> getAdviceById(Long id);

    boolean addAdvice(Advice advice);

    boolean updateAdvice(Advice advice);

    boolean checkIfAdviceExists(Long id);




}
