package com.zt.demo.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * 正如这个工厂接口的名字所示，这个工厂接口最大的特点就是可以列出工厂可以生产的所有实例。
 * 当然，工厂并没有直接提供返回所有实例的方法，也没这个必要。它可以返回指定类型的所有的实例。
 * 而且你可以通过getBeanDefinitionNames()得到工厂所有bean的名字，然后根据这些名字得到所有的Bean。
 * 这个工厂接口扩展了BeanFactory的功能，作为上文指出的BeanFactory二级接口，有9个独有的方法，
 * 扩展了跟BeanDefinition的功能，提供了BeanDefinition、BeanName、注解有关的各种操作。
 * 它可以根据条件返回Bean的集合，这就是它名字的由来——ListableBeanFactory。
 * 
 * 即ListableBeanFactory是beanFactory接口的扩展接口，它可以枚举所有的bean实例，而不是客户端通过名称一个一个的查询得出所有的实例。
 * 要预加载所有的bean定义的beanfactory可以实现这个接口来。该 接口定义了访问容器中Bean基本信息的若干方法，如查看Bean的个数、
 * 获取某一类型Bean的配置名、查看容器中是否包括某一Bean等方法.
 */
public interface ListableBeanFactory extends BeanFactory {
    
    /** 检查此bean工厂是否包含具有给定名称的bean定义。*/
    boolean containsBeanDefinition(String beanName);
    
    /**返回工厂中定义的bean数量。*/
    int getBeanDefinitionCount();
    
    /**返回此工厂中定义的所有bean的名称。*/
    String[] getBeanDefinitionNames();
    
    /**返回与给定类型(包括子类)匹配的bean的名称*/
    String[] getBeanNamesForType(ResolvableType type);
    
    /**返回与给定类型匹配的bean的名称(包括子类)*/
    String[] getBeanNamesForType(@Nullable Class<?> type);
    
    /**返回与给定类型匹配的bean的名称(包括子类)*/
    String[] getBeanNamesForType(@Nullable Class<?> type, boolean includeNonSingletons, boolean allowEagerInit);
    
    /**返回匹配给定对象类型的bean实例*/
    <T> Map<String, T> getBeansOfType(@Nullable Class<T> type) throws BeansException;
    
    /**返回匹配给定对象类型的bean实例*/
    <T> Map<String, T> getBeansOfType(@Nullable Class<T> type, boolean includeNonSingletons, boolean allowEagerInit)
            throws BeansException;
    
    /**查找所有bean的名称，这些bean的{@code类}具有提供的{@link注释}类型，但尚未创建任何bean实例。*/
    String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotationType);
    
    /**查找其{@code类}具有提供的{@link注释}类型的所有bean，返回具有对应bean实例的bean名称映射。*/
    Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) throws BeansException;
    
    /**根据指定Bean名和注解类型查找指定的Bean。若没有，则遍历其接口和超类*/
    @Nullable
    <A extends Annotation> A findAnnotationOnBean(String beanName, Class<A> annotationType)
            throws NoSuchBeanDefinitionException;
}
