package com.zt.demo.eventBus2;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import com.zt.demo.entity.DeUser;
import com.zt.demo.service.DeUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhutong
 * @date 2019/1/23 18:11
 */
@Component
@Slf4j
public class MultipleListener {
    
    public DeUser deUser;
    public Long lastLong;
    @Autowired
    private DeUserService deUserService;
    
    @Subscribe
    @AllowConcurrentEvents
    public void listenInteger(DeUser deUser) {
    
        System.out.println("事件开始执行！！！");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        deUserService.insert(deUser);
        System.out.println("event Integer:"+deUser.toString());
        System.out.println("事件执行结束！！！");
        log.info("异步方法内部线程名称：{}!", Thread.currentThread().getName());
    }
    
    @Subscribe
    public void listenLong(String event) {
        System.out.println("event Long:"+event);
    }
}
