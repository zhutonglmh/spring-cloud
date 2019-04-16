package com.zt.demo.eventBus2;

import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhutong
 * @date 2019/1/23 18:11
 */
@Component
public class EventRegister {
    @Autowired
    public EventRegister(EventBus eventBus, MultipleListener  listener) {
        // TODO Auto-generated constructor stub
        eventBus.register(listener);
    }
}
