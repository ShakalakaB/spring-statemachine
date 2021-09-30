package spring.statemachine.service.impl;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;
import spring.statemachine.domain.PaymentEvent;
import spring.statemachine.domain.PaymentState;
import spring.statemachine.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    private static final String ORDER_ID_HEADER = "orderId";

    @Override
    public StateMachine<PaymentState, PaymentEvent> pay(Long orderId, String paymentConfirmationNumber) {
       return null;
    }
}
