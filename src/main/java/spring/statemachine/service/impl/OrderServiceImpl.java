package spring.statemachine.service.impl;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;
import spring.statemachine.constant.OrderEventEnum;
import spring.statemachine.constant.OrderStateEnum;
import spring.statemachine.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    private static final String ORDER_ID_HEADER = "orderId";

    @Override
    public StateMachine<OrderStateEnum, OrderEventEnum> pay(Long orderId, String paymentConfirmationNumber) {
        StateMachine<OrderStateEnum, OrderEventEnum> sm = this.build(orderId);

        Message<OrderEventEnum> paymentMessage = MessageBuilder.withPayload(OrderEventEnum.PAY)
                .setHeader(ORDER_ID_HEADER, orderId)
                .setHeader("paymentConfirmationNumber", paymentConfirmationNumber)
                .build();

        sm.sendEvent(paymentMessage);

        return sm;
    }
}
