//package com.zt.demo.eventBus;
//
//import com.google.common.eventbus.EventBus;
//import org.springframework.stereotype.Component;
//
///**
// * @author zhutong
// * @date 2019/1/23 16:38
// */
//@Component
//public class AppcontxtListener implements EventPark {
//   
//    private final EventBus eventBus = new EventBus();
//
//    private final Listener listener = new OneListener();
//
//    public AppcontxtListener(){
//        register(getListener());
//    }
//
//    @Override
//    public void post(EventDemo eventObject) {
//        getEventBus().post(eventObject);
//    }
//
//    @Override
//    public void register(Listener listener) {
//        getEventBus().register(listener);
//    }
//
//    @Override
//    public void unregister(Listener listener) {
//        getEventBus().unregister(listener);
//    }
//
//    @Override
//    public EventBus getEventBus() {
//        return eventBus;
//    }
//
//    @Override
//    public Listener getListener() {
//        return listener;
//    }
//}
