package com.zt.demo.event;

import com.zt.demo.entity.DeUser;
import com.zt.demo.service.DeUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author zhutong
 * @date 2018/12/26 15:15
 */
@Component
@Slf4j
public class ScmStockInEventListener {
    
    @Autowired
    private DeUserService deUserService;
    
    @EventListener
    @Async("asyncPoolTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT,fallbackExecution = true)
    public void processBlackListEvent(ScmStockInEvent event) {
    
        DeUser deUser = event.getDeUser();
        //todo 逻辑
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        deUserService.insert(deUser);
        System.out.println("事件开始执行！");
//        System.out.println(event.getSource());
//        Demo demo = (Demo)event.getSource();
//        demo.getName();
        log.info("异步方法内部线程名称：{}!", Thread.currentThread().getName());
    }
}
