package spring.statemachine.constant;

import lombok.Getter;

public enum OrderStateEnum {
    SUBMITTED("submitted", (short) 100),
    PAID("paid", (short) 200),
    FULFILLED("fulfilled", (short) 300),
    CANCELLED("cancelled", (short) 400),
    ;

    @Getter
    private String desc;

    @Getter
    private Short code;

    OrderStateEnum(String desc, Short code) {
        this.desc = desc;
        this.code = code;
    }
}
