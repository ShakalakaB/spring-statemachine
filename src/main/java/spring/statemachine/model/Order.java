package spring.statemachine.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private Short state;

    private LocalDateTime createdTime;


}