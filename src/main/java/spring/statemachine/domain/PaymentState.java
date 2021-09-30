package spring.statemachine.domain;

import lombok.Getter;

public enum PaymentState {
    SUBMITTED,
    PRE_AUTH,
    PRE_AUTH_ERROR,
    AUTH,
    AUTH_ERROR,
    ;
}
