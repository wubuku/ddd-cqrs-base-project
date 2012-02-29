package org.nthdimenzion.ddd.infrastructure.spring;

import org.nthdimenzion.ddd.infrastructure.exception.ErrorDetails;
import org.nthdimenzion.ddd.infrastructure.exception.ErrorMessageLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationSetup implements ApplicationContextAware,IApplicationContext {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationSetup.class);

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        ErrorMessageLocator errorMessageLocator = applicationContext.getBean("errorMessageLocator", ErrorMessageLocator.class);
        ErrorDetails.errorMessageLocator = errorMessageLocator;
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return applicationContext.getBean(requiredType);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return applicationContext.getBean(name, args);
    }

}
