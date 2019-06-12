package com.zt.demo.event;

import com.zt.demo.entity.DeUser;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * @author zhutong
 * @date 2018/12/26 14:39
 */
@Data
public class ScmStockInEvent extends ApplicationEvent {
    
    private DeUser deUser;
    
    public ScmStockInEvent(Object source, DeUser deUser){
        super(source);
        this.deUser = deUser;
    }
}