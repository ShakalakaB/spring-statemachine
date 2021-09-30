package spring.statemachine.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;
import spring.statemachine.domain.Payment;
import spring.statemachine.domain.PaymentEvent;
import spring.statemachine.domain.PaymentState;
import spring.statemachine.repository.PaymentRepository;
import spring.statemachine.service.impl.PaymentServiceImpl;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentStateChangeInterceptor extends StateMachineInterceptorAdapter<PaymentState, PaymentEvent> {
    private final PaymentRepository paymentRepository;

    @Override
    public void preStateChange(State<PaymentState, PaymentEvent> state, Message<PaymentEvent> message,
                               Transition<PaymentState, PaymentEvent> transition,
                               StateMachine<PaymentState, PaymentEvent> stateMachine) {
        Optional.ofNullable(message)
                .flatMap(msg -> Optional.ofNullable((Long) msg.getHeaders().getOrDefault(PaymentServiceImpl.PAYMENT_ID_HEADER, -1L)))
                .ifPresent(paymentId -> {
            Payment payment = paymentRepository.getById(paymentId);
            payment.setState(state.getId());
            paymentRepository.save(payment);
        });
    }
}
