package com.zt.demo.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.config.*;
import org.springframework.lang.Nullable;

import java.util.Set;

/**
 * 在BeanFactory基础上实现对已存在实例的管理.
 *
 * 总共5个静态不可变常量来指明装配策略，其中一个常量被Spring3.0废弃、一个常量表示没有自动装配，另外3个常量指明不同的装配策略——根据名称、根据类型、根据构造方法。
 * 8个跟自动装配有关的方法，实在是繁杂，具体的意义我们研究类的时候再分辨吧。
 * 2个执行BeanPostProcessors的方法。
 * 2个分解指定依赖的方法
 *
 *  对于想要拥有自动装配能力，并且想要把这种能力暴露给外部应用BeanFactory类需要实现此接口。
 *  正常情况下不要使用此接口，应该更倾向于使用BeanFactory或者ListableBeanFactory接口。
 * 一般应用开发者不会使用这个接口,所以像ApplicationContext这样的外观实现类不会实现这个接口,如果真手痒痒可以通过ApplicationContext的getAutowireCapableBeanFactory接口获取. 
 * 总结：这个工厂接口继承自BeanFacotory，它扩展了自动装配的功能，根据类定义BeanDefinition装配Bean、执行前、后处理器等
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    
    /**没有外部定义的自动装配*/
    int AUTOWIRE_NO = 0;
    
    /**常量，该常量通过名称指示自动装配bean属性*/
    int AUTOWIRE_BY_NAME = 1;
    
    /***常量，该常量根据类型指示自动装配bean属性*/
    int AUTOWIRE_BY_TYPE = 2;
    
    /**常量，标识按照贪婪策略匹配出的最符合的构造方法来自动装配的常量*/
    int AUTOWIRE_CONSTRUCTOR = 3;
    
    /**标识自动识别一种装配策略来实现自动装配的常量*/
    @Deprecated
    int AUTOWIRE_AUTODETECT = 4;
    
    /**创建一个给定Class的实例*/
    <T> T createBean(Class<T> beanClass) throws BeansException;
    
    /**　通过调用给定Bean的after-instantiation及post-processing接口，对bean进行配置*/
    void autowireBean(Object existingBean) throws BeansException;
    
    /**配置参数中指定的bean，包括自动装配其域，对其应用如setBeanName功能的回调函数。*/
    Object configureBean(Object existingBean, String beanName) throws BeansException;
    
    
    //-------------------------------------------------------------------------
    // Specialized methods for fine-grained control over the bean lifecycle
    //-------------------------------------------------------------------------
    
   /**创建一个指定class的实例，通过参数可以指定其自动装配模式（by-name or by-type）*/
    Object createBean(Class<?> beanClass, int autowireMode, boolean dependencyCheck) throws BeansException;
    
    /** 通过指定的自动装配策略来初始化一个Bean*/
    Object autowire(Class<?> beanClass, int autowireMode, boolean dependencyCheck) throws BeansException;
    
    /**  * 通过指定的自动装配方式来对给定的Bean进行自动装配。不过会调用指定Bean注册的BeanPostProcessors等回调函数来初始化Bean。如果指定装配方式为AUTOWIRE_NO的话，不会自动装配属性，但是依然会调用BeanPiostProcesser等回调方法。*/
    void autowireBeanProperties(Object existingBean, int autowireMode, boolean dependencyCheck)
            throws BeansException;
    
    /**
     * Apply the property values of the bean definition with the given name to
     * the given bean instance. The bean definition can either define a fully
     * self-contained bean, reusing its property values, or just property values
     * meant to be used for existing bean instances.
     * <p>This method does <i>not</i> autowire bean properties; it just applies
     * explicitly defined property values. Use the {@link #autowireBeanProperties}
     * method to autowire an existing bean instance.
     * <b>Note: This method requires a bean definition for the given name!</b>
     * <p>Does <i>not</i> apply standard {@link BeanPostProcessor BeanPostProcessors}
     * callbacks or perform any further initialization of the bean. This interface
     * offers distinct, fine-grained operations for those purposes, for example
     * {@link #initializeBean}. However, {@link InstantiationAwareBeanPostProcessor}
     * callbacks are applied, if applicable to the configuration of the instance.
     * @param existingBean the existing bean instance
     * @param beanName the name of the bean definition in the bean factory
     * (a bean definition of that name has to be available)
     * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
     * if there is no bean definition with the given name
     * @throws BeansException if applying the property values failed
     * @see #autowireBeanProperties
     */
    void applyBeanPropertyValues(Object existingBean, String beanName) throws BeansException;
    
    /**
     * Initialize the given raw bean, applying factory callbacks
     * such as {@code setBeanName} and {@code setBeanFactory},
     * also applying all bean post processors (including ones which
     * might wrap the given raw bean).
     * <p>Note that no bean definition of the given name has to exist
     * in the bean factory. The passed-in bean name will simply be used
     * for callbacks but not checked against the registered bean definitions.
     * @param existingBean the existing bean instance
     * @param beanName the name of the bean, to be passed to it if necessary
     * (only passed to {@link BeanPostProcessor BeanPostProcessors})
     * @return the bean instance to use, either the original or a wrapped one
     * @throws BeansException if the initialization failed
     */
    Object initializeBean(Object existingBean, String beanName) throws BeansException;
    
    /**
     * Apply {@link BeanPostProcessor BeanPostProcessors} to the given existing bean
     * instance, invoking their {@code postProcessBeforeInitialization} methods.
     * The returned bean instance may be a wrapper around the original.
     * @param existingBean the new bean instance
     * @param beanName the name of the bean
     * @return the bean instance to use, either the original or a wrapped one
     * @throws BeansException if any post-processing failed
     * @see BeanPostProcessor#postProcessBeforeInitialization
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName)
            throws BeansException;
    
    /**
     * Apply {@link BeanPostProcessor BeanPostProcessors} to the given existing bean
     * instance, invoking their {@code postProcessAfterInitialization} methods.
     * The returned bean instance may be a wrapper around the original.
     * @param existingBean the new bean instance
     * @param beanName the name of the bean
     * @return the bean instance to use, either the original or a wrapped one
     * @throws BeansException if any post-processing failed
     * @see BeanPostProcessor#postProcessAfterInitialization
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)
            throws BeansException;
    
    /**
     * Destroy the given bean instance (typically coming from {@link #createBean}),
     * applying the {@link org.springframework.beans.factory.DisposableBean} contract as well as
     * registered {@link DestructionAwareBeanPostProcessor DestructionAwareBeanPostProcessors}.
     * <p>Any exception that arises during destruction should be caught
     * and logged instead of propagated to the caller of this method.
     * @param existingBean the bean instance to destroy
     */
    void destroyBean(Object existingBean);
    
    
    //-------------------------------------------------------------------------
    // Delegate methods for resolving injection points
    //-------------------------------------------------------------------------
    
    /**
     * Resolve the bean instance that uniquely matches the given object type, if any,
     * including its bean name.
     * <p>This is effectively a variant of {@link #getBean(Class)} which preserves the
     * bean name of the matching instance.
     * @param requiredType type the bean must match; can be an interface or superclass.
     * {@code null} is disallowed.
     * @return the bean name plus bean instance
     * @throws NoSuchBeanDefinitionException if no matching bean was found
     * @throws NoUniqueBeanDefinitionException if more than one matching bean was found
     * @throws BeansException if the bean could not be created
     * @since 4.3.3
     * @see #getBean(Class)
     */
    <T> NamedBeanHolder<T> resolveNamedBean(Class<T> requiredType) throws BeansException;
    
    /**
     * Resolve the specified dependency against the beans defined in this factory.
     * @param descriptor the descriptor for the dependency (field/method/constructor)
     * @param requestingBeanName the name of the bean which declares the given dependency
     * @return the resolved object, or {@code null} if none found
     * @throws NoSuchBeanDefinitionException if no matching bean was found
     * @throws NoUniqueBeanDefinitionException if more than one matching bean was found
     * @throws BeansException if dependency resolution failed for any other reason
     * @since 2.5
     * @see #resolveDependency(DependencyDescriptor, String, Set, TypeConverter)
     */
    @Nullable
    Object resolveDependency(DependencyDescriptor descriptor, @Nullable String requestingBeanName) throws BeansException;
    
    /**
     * Resolve the specified dependency against the beans defined in this factory.
     * @param descriptor the descriptor for the dependency (field/method/constructor)
     * @param requestingBeanName the name of the bean which declares the given dependency
     * @param autowiredBeanNames a Set that all names of autowired beans (used for
     * resolving the given dependency) are supposed to be added to
     * @param typeConverter the TypeConverter to use for populating arrays and collections
     * @return the resolved object, or {@code null} if none found
     * @throws NoSuchBeanDefinitionException if no matching bean was found
     * @throws NoUniqueBeanDefinitionException if more than one matching bean was found
     * @throws BeansException if dependency resolution failed for any other reason
     * @since 2.5
     * @see DependencyDescriptor
     */
    @Nullable
    Object resolveDependency(DependencyDescriptor descriptor, @Nullable String requestingBeanName,
                             @Nullable Set<String> autowiredBeanNames, @Nullable TypeConverter typeConverter) throws BeansException;
}
