package pl.coderslab.advice;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    @Min(value = 1, message = "Musi być wieksze niż 1")
    @Max(value = 5, message = "Musi byc mniejsze niż 5")
    private int value;

    @ManyToOne
    private Advice advice;

    @Column(name = "createdOn")
    private LocalDateTime createdOn;

    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

//    public Advice getAdvice() {
//        return advice;
//    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
}
