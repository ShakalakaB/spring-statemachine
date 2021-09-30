package spring.statemachine.service;

import org.springframework.statemachine.StateMachine;
import spring.statemachine.domain.PaymentEvent;
import spring.statemachine.domain.PaymentState;

public interface OrderService {
    StateMachine<PaymentState, PaymentEvent> pay(Long orderId, String paymentConfirmNumber);
}
