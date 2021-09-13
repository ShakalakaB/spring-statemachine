package spring.statemachine.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.statemachine.constant.OrderStateEnum;
import spring.statemachine.model.Order;
import spring.statemachine.repository.OrderRepository;

@Component
@RequiredArgsConstructor
public class DataBootstrap implements CommandLineRunner {
    private final OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        Order order1 = new Order();
        order1.setId(1L);
        order1.setState(OrderStateEnum.SUBMITTED.getCode());
    }
}
