package spring.statemachine.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.statemachine.constant.OrderStateEnum;
import spring.statemachine.model.LemonOrder;
import spring.statemachine.repository.OrderRepository;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataBootstrap implements CommandLineRunner {
    private final OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        LemonOrder order1 = new LemonOrder();
        order1.setId(1L);
        order1.setState(OrderStateEnum.SUBMITTED.getCode());
        order1.setCreatedTime(LocalDateTime.now());
        orderRepository.save(order1);

        LemonOrder order2 = new LemonOrder();
        order2.setId(1L);
        order2.setState(OrderStateEnum.SUBMITTED.getCode());
        order2.setCreatedTime(LocalDateTime.now());

        orderRepository.save(order2);

        System.out.println("Orders loaded = " + orderRepository.count());
    }
}
