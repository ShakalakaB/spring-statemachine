package spring.statemachine.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.transaction.annotation.Transactional;
import spring.statemachine.domain.Payment;
import spring.statemachine.domain.PaymentEvent;
import spring.statemachine.domain.PaymentState;
import spring.statemachine.repository.PaymentRepository;
import spring.statemachine.service.PaymentService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentServiceImplTest {
    @Autowired
    PaymentService paymentService;

    @Autowired
    PaymentRepository paymentRepository;

    Payment payment;

    @BeforeEach
    void setUp() {
        this.payment = Payment.builder().amount(new BigDecimal("12.99")).build();
    }

    @Test
    @Transactional
    void preAuthApprove() {
        Payment payment = paymentService.createPayment(this.payment);
        assertEquals(PaymentState.SUBMITTED, payment.getState());

        StateMachine<PaymentState, PaymentEvent> stateMachine = paymentService.preAuthApprove(payment.getId());
        Payment preAuthPayment = paymentRepository.getById(payment.getId());
        assertEquals(PaymentState.PRE_AUTH, preAuthPayment.getState());
        assertEquals(PaymentState.PRE_AUTH, stateMachine.getState().getId());
    }
}