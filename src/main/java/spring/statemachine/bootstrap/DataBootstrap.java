package spring.statemachine.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.statemachine.domain.PaymentState;
import spring.statemachine.repository.PaymentRepository;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataBootstrap implements CommandLineRunner {
    private final PaymentRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
    }
}
