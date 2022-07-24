package com.willing.cargo.listener;

import com.willing.cargo.enume.OrderStatusChangeEvent;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.OnTransitionEnd;
import org.springframework.statemachine.annotation.OnTransitionStart;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

/**
 * @author Szg
 * @ClassName:
 * @Description:
 * @date 2022/7/2322:51
 */
//@Component
//@WithStateMachine(id = "orderStateMachineId")
public class OrderStateListener {

    @OnTransition(source = "",target = "")
    @OnTransitionEnd
    @OnTransitionStart
    public void payTransition(Message<OrderStatusChangeEvent> message){

    }
}
