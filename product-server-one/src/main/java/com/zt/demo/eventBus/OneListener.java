//package com.zt.demo.eventBus;
//
//import com.google.common.eventbus.Subscribe;
//import com.zt.demo.entity.DeUser;
//import com.zt.demo.service.DeUserService;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
///**
// * @author zhutong
// * @date 2019/1/23 16:39
// */
//@Component
//public class OneListener{
//    
//    @Resource
//    private DeUserService deUserService;
//    
//    /**
//     * 消费方法
//     *
//     * @param event 事件
//     */
//    @Subscribe
//    public void consume(EventDemo event) {
//        DeUser deUser = event.getDeUser();
//        //todo 逻辑
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        deUserService.insert(deUser);
//        System.out.println("事件开始执行！"); 
//    }
//}
