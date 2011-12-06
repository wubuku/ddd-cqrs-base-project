package org.nthdimenzion.cqrs.command;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.nthdimenzion.cqrs.command.annotations.CommandHandler;
import org.nthdimenzion.ddd.infrastructure.exception.ErrorDetails;
import org.nthdimenzion.ddd.infrastructure.exception.ErrorMessageLocator;
import org.nthdimenzion.object.utils.UtilValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.nthdimenzion.cqrs.command.annotations.Command;
import java.lang.reflect.Method;
import java.util.Map;

@Component
public class SpringBasedMultiCommandHandlerRegistry implements IMultiCommandHandlerRegistry, ApplicationContextAware, DestructionAwareBeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(SpringBasedMultiCommandHandlerRegistry.class);
    protected ApplicationContext applicationContext;
    private final Map<Class<?>, Handler> handlerCache = Maps.newHashMap();

    protected SpringBasedMultiCommandHandlerRegistry(){
    logger.debug("Constructor");
    }

    @Override
    public Handler findCommandHandlerFor(Class<? extends ICommand> commandType) {
        Preconditions.checkNotNull(commandType);
        Handler handler = handlerCache.get(commandType);
        if (handler == null) {
            logger.error("No valid command Handler found for " + commandType.getClass().getName());
            throw new NoCommandHandlerFoundException(commandType.getClass().getName());
        }
        return handler;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        logger.debug("Destruction");
        handlerCache.remove(bean.getClass());
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class commandHandlerClass = bean.getClass();
        if (isCommandHandler(commandHandlerClass)) {
            logger.debug("Entry postProcessAfterInitialization " + beanName);
            populateHandlerCache(bean,handlerCache);
        }
        return bean;
    }

    private Map populateHandlerCache(Object commandHandlerBean, Map<Class<?>, Handler> handlerCache) {
        Class commandHandlerClass = commandHandlerBean.getClass();
        Method[] methods = commandHandlerClass.getMethods();
        if(UtilValidator.isNotEmpty(methods)){
            for (Method commandHandlingMethod : methods){
                if(isCommandHandlingMethod(commandHandlingMethod)){
                    Class<?> commandClass = getCommandParamFrom(commandHandlingMethod);
                    Handler handler = new Handler(commandHandlingMethod,commandHandlerBean);
                    Handler previousHandler = handlerCache.put(commandClass, handler);
                    if(previousHandler!=null){
                        logger.warn(previousHandler.toString() + "is getting overrridden by " + handler.toString());
                    }

                }
            }
        }
        return handlerCache;
    }

    private Class<?> getCommandParamFrom(Method commandHandlingMethod) {
        Class<?> methodParams[] = commandHandlingMethod.getParameterTypes();
        return methodParams[0];
    }

    private boolean isCommandHandlingMethod(Method method) {
        boolean isCommandHandlingMethod = false;
        Class<?> methodParams[] = method.getParameterTypes();
        if(UtilValidator.isNotEmpty(methodParams)){
            if(methodParams.length != 1)
                return false;
            for(Class methodParam : methodParams){
                if(methodParam.isAnnotationPresent(Command.class)){
                    isCommandHandlingMethod = true;
                    break;
                }
            }
        }
        return isCommandHandlingMethod;
    }

    private boolean isCommandHandler(Class<?> beanClass) {
        return beanClass.isAnnotationPresent(CommandHandler.class);
    }
}
