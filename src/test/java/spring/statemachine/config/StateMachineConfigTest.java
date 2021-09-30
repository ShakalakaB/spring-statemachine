package spring.statemachine.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import spring.statemachine.domain.PaymentEvent;
import spring.statemachine.domain.PaymentState;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StateMachineConfigTest {
    @Autowired
    StateMachineFactory<PaymentState, PaymentEvent> factory;

    @Test
    void configure() {
        StateMachine<PaymentState, PaymentEvent> stateMachine = factory.getStateMachine(UUID.randomUUID());
        stateMachine.start();
        System.out.println(stateMachine.getState().toString());

        stateMachine.sendEvent(PaymentEvent.PRE_AUTHORIZE);
        System.out.println(stateMachine.getState().toString());

        stateMachine.sendEvent(PaymentEvent.PRE_AUTH_DECLINED);
        System.out.println(stateMachine.getState().toString());
    }
}