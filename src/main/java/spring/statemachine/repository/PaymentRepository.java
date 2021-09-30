package spring.statemachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.statemachine.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
