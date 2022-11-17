package pl.coderslab.advice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
