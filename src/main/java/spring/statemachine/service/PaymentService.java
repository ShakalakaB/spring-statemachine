package spring.statemachine.service;

import org.springframework.statemachine.StateMachine;
import spring.statemachine.domain.Payment;
import spring.statemachine.domain.PaymentEvent;
import spring.statemachine.domain.PaymentState;

public interface PaymentService {
    Payment createPayment(Payment payment);

    StateMachine<PaymentState, PaymentEvent> preAuth(Long paymentId);

    StateMachine<PaymentState, PaymentEvent> authorizePayment(Long paymentId);
}
