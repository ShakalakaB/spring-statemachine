package spring.statemachine.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import spring.statemachine.constant.OrderEventEnum;
import spring.statemachine.constant.OrderStateEnum;

import java.util.EnumSet;

@Slf4j
@Configuration
@EnableStateMachineFactory
public class StatemachineConfiguration extends StateMachineConfigurerAdapter<OrderStateEnum, OrderEventEnum> {
    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderStateEnum, OrderEventEnum> config) throws Exception {
        StateMachineListenerAdapter<OrderStateEnum, OrderEventEnum> adapter = new StateMachineListenerAdapter<OrderStateEnum, OrderEventEnum>() {
            @Override
            public void stateChanged(State<OrderStateEnum, OrderEventEnum> from, State<OrderStateEnum, OrderEventEnum> to) {
                log.info(String.format("stateChanged(from: %s, to: %s)", from + "", to + ""));
            }
        };

        config.withConfiguration()
                .autoStartup(false)
                .listener(adapter);
    }

    @Override
    public void configure(StateMachineStateConfigurer<OrderStateEnum, OrderEventEnum> states) throws Exception {
        states.withStates()
                .initial(OrderStateEnum.SUBMITTED)
                .state(OrderStateEnum.PAID)
                .end(OrderStateEnum.FULFILLED)
                .end(OrderStateEnum.CANCELLED);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStateEnum, OrderEventEnum> transitions) throws Exception {
        transitions
                .withExternal().source(OrderStateEnum.SUBMITTED).target(OrderStateEnum.PAID).event(OrderEventEnum.PAY)
                .and()
                .withExternal().source(OrderStateEnum.PAID).target(OrderStateEnum.FULFILLED).event(OrderEventEnum.FULFILL)
                .and()
                .withExternal().source(OrderStateEnum.SUBMITTED).target(OrderStateEnum.CANCELLED).event(OrderEventEnum.CANCEL)
                .and()
                .withExternal().source(OrderStateEnum.PAID).target(OrderStateEnum.CANCELLED).event(OrderEventEnum.CANCEL);
    }
}
