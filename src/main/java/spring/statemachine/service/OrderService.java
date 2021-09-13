package spring.statemachine.service;

import org.springframework.statemachine.StateMachine;
import spring.statemachine.constant.OrderEventEnum;
import spring.statemachine.constant.OrderStateEnum;

public interface OrderService {
    StateMachine<OrderStateEnum, OrderEventEnum> pay(Long orderId, String paymentConfirmNumber);
}
