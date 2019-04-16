package com.zt.demo.ioc;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.lang.Nullable;

/**
 * 由bean工厂实现的子接口，可以是其中的一部分
 * 等级制度的。
 */
public interface HierarchicalBeanFactory extends BeanFactory{
    
    /**返回本Bean工厂的父工厂。这个方法实现了工厂的分层。*/
    @Nullable
    BeanFactory getParentBeanFactory();
    
    /**返回本地bean工厂是否包含给定名称的bean*/
    boolean containsLocalBean(String name);
}
