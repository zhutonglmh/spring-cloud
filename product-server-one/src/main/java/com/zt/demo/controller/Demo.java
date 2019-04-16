package com.zt.demo.controller;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.zt.demo.entity.DeUser;
import com.zt.demo.event.ScmStockInEvent;
import com.zt.demo.eventBus2.MultipleListener;
import com.zt.demo.service.DeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.Future;

@RequestMapping("demo")
@RestController
public class Demo {
    
    
    @Autowired
    private ApplicationContext publisher;
    
    @Autowired
    ThreadPoolTaskExecutor asyncPoolTaskExecutor;
    
    @Autowired
    public AsyncEventBus eventBus;
    
    @Autowired
    public MultipleListener listener;
    
    @GetMapping("/demo")
    public String demo() {
        
//        DeUser deUser = buildMes();
//        ScmStockInEvent event = new ScmStockInEvent(this, deUser);
//        publisher.publishEvent(event);
        
        DeUser deUser = buildMes();
        eventBus.post(deUser);
        eventBus.post(buildMes());
        eventBus.post(buildMes());
        System.out.println("事件发布成功");

        
        return "demo";
    }
    
    private DeUser buildMes(){
        DeUser deUser = new DeUser();
        deUser.setId(UUID.randomUUID().toString());
        deUser.setName("demo");
        deUser.setAge(22);
        deUser.setScoot(100);
        return deUser;
    }
    
    public void getName(){
        System.out.println("朱同");
    }
}
