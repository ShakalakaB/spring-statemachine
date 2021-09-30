package spring.statemachine.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.statemachine.domain.Payment;
import spring.statemachine.domain.PaymentEvent;
import spring.statemachine.domain.PaymentState;
import spring.statemachine.repository.PaymentRepository;
import spring.statemachine.service.PaymentService;
import spring.statemachine.service.PaymentStateChangeInterceptor;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    public static final String PAYMENT_ID_HEADER = "payment_id";

    private final PaymentRepository paymentRepository;

    private final StateMachineFactory<PaymentState, PaymentEvent> stateMachineFactory;

    private final PaymentStateChangeInterceptor paymentStateChangeInterceptor;

    @Override
    @Transactional
    public Payment createPayment(Payment payment) {
        payment.setState(PaymentState.SUBMITTED);
        return paymentRepository.save(payment);
    }

    @Override
    @Transactional
    public StateMachine<PaymentState, PaymentEvent> preAuthApprove(Long paymentId) {
        StateMachine<PaymentState, PaymentEvent> stateMachine = build(paymentId);
        sendEvent(paymentId, stateMachine, PaymentEvent.PRE_AUTH_APPROVED);
        return stateMachine;
    }

    @Override
    @Transactional
    public StateMachine<PaymentState, PaymentEvent> authorizePaymentApprove(Long paymentId) {
        StateMachine<PaymentState, PaymentEvent> stateMachine = build(paymentId);
        sendEvent(paymentId, stateMachine, PaymentEvent.AUTH_APPROVED);
        return stateMachine;
    }

    private StateMachine<PaymentState, PaymentEvent> build(Long paymentId) {
        Payment payment = paymentRepository.getById(paymentId);
        StateMachine<PaymentState, PaymentEvent> stateMachine = stateMachineFactory.getStateMachine(Long.toString(payment.getId()));

        stateMachine.start();
        stateMachine.getStateMachineAccessor()
                .doWithAllRegions(stateMachineAccessor -> {
                    stateMachineAccessor.addStateMachineInterceptor(paymentStateChangeInterceptor);
                    stateMachineAccessor.resetStateMachine(new DefaultStateMachineContext<>(payment.getState(), null, null, null));
                });
        stateMachine.start();

        return stateMachine;
    }

    private void sendEvent(Long paymentId, StateMachine<PaymentState, PaymentEvent> stateMachine, PaymentEvent event) {
        Message<PaymentEvent> msg = MessageBuilder.withPayload(event)
                .setHeader(PAYMENT_ID_HEADER, paymentId)
                .build();
        stateMachine.sendEvent(msg);
    }
}
