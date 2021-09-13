package spring.statemachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.statemachine.model.LemonOrder;

public interface OrderRepository extends JpaRepository<LemonOrder, Long> {
}
