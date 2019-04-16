package com.zt.demo.ioc.beanDifinition;


import org.springframework.beans.BeanMetadataElement;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.*;
import org.springframework.core.AttributeAccessor;
import org.springframework.lang.Nullable;

/**
 *  一个BeanDefinition描述了一个bean的实例，包括属性值，构造方法参数值和继承自它的类的更多信息。
 *  BeanDefinition仅仅是一个最简单的接口，
 *  主要功能是允许BeanFactoryPostProcessor 
 *  例如PropertyPlaceHolderConfigure 能够检索并修改属性值和别的bean的元数据
 * */
public interface BeanDefinition extends AttributeAccessor, BeanMetadataElement {
    
    /**标准单例作用域的作用域标识符：“singleton”。*/
    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;
    
    /**标准原型作用域的范围标识符：“prototype”*/
    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;
    
    
    /**表示BeanDefinition是应用程序主要部分的角色提示。 通常对应于用户定义的bean。*/
    int ROLE_APPLICATION = 0;
    
    /**实际上就是说，我这个Bean是用户的，是从配置文件中过来的*/
    int ROLE_SUPPORT = 1;
    
    /**就是这Bean是Spring自己的*/
    int ROLE_INFRASTRUCTURE = 2;
    
    //1、 当前Bean父类名称get&set方法
    /**如果父类存在，设置这个bean定义的父定义的名称。*/
    void setParentName(@Nullable String parentName);
    
    /**如果父类存在，则返回当前Bean的父类的名称*/
    @Nullable
    String getParentName();
    
    /**指定此bean定义的bean类名称。*/
    void setBeanClassName(@Nullable String beanClassName);
    
    /**返回此bean定义的当前bean类名称，需要注意的是，这不一定是在运行时使用的实际类名，以防子类定义覆盖/继承其父类的类名*/
    @Nullable
    String getBeanClassName();
    
    /**覆盖此bean的目标范围，指定一个新的范围名称*/
    void setScope(@Nullable String scope);
    
    /**返回此bean的当前目标作用域的名称，如果没有确定，返回null*/
    @Nullable
    String getScope();
    
    /**置这个bean是否应该被延迟初始化。如果{false}，那么这个bean将在启动时由bean工厂实例化，只适用于单例bean*/
    void setLazyInit(boolean lazyInit);
    
    /**返回这个bean是否应该被延迟初始化，即不是在启动时立即实例化。只适用于单例bean*/
    boolean isLazyInit();
    
    /**设置这个bean依赖被初始化的bean的名字。 bean工厂将保证这些bean首先被初始化*/
    void setDependsOn(@Nullable String... dependsOn);
    
    /**返回这个bean依赖的bean名称。*/
    @Nullable
    String[] getDependsOn();
    
    /**返设置这个bean是否是获得自动装配到其他bean的候选人*/
    void setAutowireCandidate(boolean autowireCandidate);
    
    /**是否为被自动装配，就是是否在其他类中使用autowired来注入当前Bean的，*/
    boolean isAutowireCandidate();
    
    /**是否为主候选bean    使用注解：@Primary*/
    void setPrimary(boolean primary);
    
    /**返回这个bean是否是主要的autowire候选者。*/
    boolean isPrimary();
    
    /**指定要使用的工厂bean（如果有的话）。 这是调用指定的工厂方法的bean的名称 */
    void setFactoryBeanName(@Nullable String factoryBeanName);
    
    /**回工厂bean的名字，如果有的话*/
    @Nullable
    String getFactoryBeanName();
    
    /**如果有的话，指定工厂方法。
     *这个方法先将通过构造函数参数被调用，或者如果参数，将调用该方法的无参数构造。
     *方法将在指定的工厂bean（如果有的话）上被调用，或者作为本地bean类的静态方法被调用*/
    void setFactoryMethodName(@Nullable String factoryMethodName);
    
    /**如果存在，返回工厂方法名*/
    @Nullable
    String getFactoryMethodName();
    
    /**返回此bean的构造函数参数值*/
    ConstructorArgumentValues getConstructorArgumentValues();
    
    /**如果有为这个bean定义的构造函数参数值，则返回*/
    default boolean hasConstructorArgumentValues() {
        return !getConstructorArgumentValues().isEmpty();
    }
    
    /**获取普通属性集合*/
    MutablePropertyValues getPropertyValues();
    
    /**是否有属性值*/
    default boolean hasPropertyValues() {
        return !getPropertyValues().isEmpty();
    }
    
    
    // Read-only attributes
    
    /**是否是单例的*/
    boolean isSingleton();
    
    /**是否是多例的*/
    boolean isPrototype();
    
    /**isAbstract*/
    boolean isAbstract();
    
    /**获取这个bean的应用*/
    int getRole();
    
    /**返回对bean定义的可读描述*/
    @Nullable
    String getDescription();
    
   /**返回该bean定义来自的资源的描述*/
    @Nullable
    String getResourceDescription();
    
    /**返回原始的BeanDefinition;如果没有，则返回null。允许检索装饰的bean定义（如果有的话）*/
    @Nullable
    org.springframework.beans.factory.config.BeanDefinition getOriginatingBeanDefinition();
}
