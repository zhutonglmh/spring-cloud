package com.zt.demo.eventBus;

import com.zt.demo.entity.DeUser;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 
 * eventBus 事件机制
 * @author zhutong
 * @date 2019/1/23 16:30
 */
@Data
@ToString
public class EventDemo implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * 数据信息
     */
    private DeUser deUser;

    public EventDemo(String id,DeUser deUser){
        this.id = id;
        this.deUser = deUser;
    }

}
