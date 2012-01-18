package org.nthdimenzion.cqrs.command;

import com.google.common.base.Preconditions;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import static com.google.common.collect.Maps.newConcurrentMap;

//@Component
@Deprecated
public class SpringBasedCommandHandlerRegistry implements ICommandHandlerRegistry, ApplicationContextAware, DestructionAwareBeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(SpringBasedCommandHandlerRegistry.class);

    protected ApplicationContext applicationContext;
    public Map<Class<? extends ICommand>, String> commandTypeToCommandHandlerName = newConcurrentMap();

    protected SpringBasedCommandHandlerRegistry(){
    logger.debug("Constructor");
    }

    @Override
    public ICommandHandler findCommandHandlerFor(Class<? extends ICommand> commandType) {
        Preconditions.checkNotNull(commandType);
        String commandHandlerName = commandTypeToCommandHandlerName.get(commandType);
        if (StringUtils.isEmpty(commandHandlerName)) {
            logger.error("No valid command Handler found for " + commandType.getClass().getName() + " commandTypeToCommandHandlerName " + commandTypeToCommandHandlerName);
            throw new NoCommandHandlerFoundException(commandType.getClass().getName());
        }
        logger.debug("Finding command Handler " + commandHandlerName + " in bean context " + applicationContext.getBean(commandHandlerName));
        return (ICommandHandler) applicationContext.getBean(commandHandlerName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        logger.debug("Destruction");
        commandTypeToCommandHandlerName.remove(bean.getClass());
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class clazz = bean.getClass();
        if (isCommandHandlerSubclass(clazz)) {
            logger.debug("Entry postProcessAfterInitialization " + beanName);
            logger.debug("Command name is " + getHandledCommandType(bean).getSimpleName());
            Class<? extends ICommand> commandType = (Class<? extends ICommand>) getHandledCommandType(bean);
            if (commandTypeToCommandHandlerName.containsKey(commandType)) {
                logger.warn("Value for " + commandType + " is currently " + commandTypeToCommandHandlerName.get(commandType) + " and will be overwritten by " + beanName);
            }
            commandTypeToCommandHandlerName.put(commandType, beanName);
            logger.debug(commandTypeToCommandHandlerName.toString());
        }
        return bean;
    }

    private boolean isCommandHandlerSubclass(Class<?> beanClass) {
        return ICommandHandler.class.isAssignableFrom(beanClass);
    }


    private Class<?> getHandledCommandType(Object object) {
        Class<?> clazz = object.getClass();
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        ParameterizedType type = findRawType(ICommandHandler.class, object);
        return (Class<?>) type.getActualTypeArguments()[0];
    }

    private ParameterizedType findRawType(Class<?> expectedRawType, Object bean) {
        ParameterizedType parameterizedType = null;
        Type[] beanInterfaces = bean.getClass().getGenericInterfaces();
        for (Type beanInterface : beanInterfaces) {
            if (isBeanAdvised(beanInterface)) {
                parameterizedType = findByRawTypeForNonAdvised(getTargetClassForAdvisedInstance(bean).getGenericInterfaces(), expectedRawType);
            }
        }
        if (isNonAdvisedBean(parameterizedType))
            parameterizedType = findByRawTypeForNonAdvised(beanInterfaces, expectedRawType);
        return parameterizedType;
    }

    private boolean isNonAdvisedBean(ParameterizedType parameterizedType) {
        return parameterizedType==null;
    }

    private boolean isBeanAdvised(Type beanInterface) {
        return beanInterface.equals(Advised.class);
    }

    private Class<?> getTargetClassForAdvisedInstance(Object object) {
        return ((Advised) object).getTargetClass();
    }


    private ParameterizedType findByRawTypeForNonAdvised(Type[] genericInterfaces, Class<?> expectedRawType) {
        for (Type type : genericInterfaces) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parametrized = (ParameterizedType) type;
                if (expectedRawType.equals(parametrized.getRawType())) {
                    return parametrized;
                }
            }
        }
        throw new RuntimeException();
    }
}
