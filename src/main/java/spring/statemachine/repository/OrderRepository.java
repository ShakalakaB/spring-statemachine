package spring.statemachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.statemachine.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
