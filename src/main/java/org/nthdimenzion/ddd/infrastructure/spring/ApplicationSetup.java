package org.nthdimenzion.ddd.infrastructure.spring;

import com.google.common.collect.Lists;
import org.nthdimenzion.ddd.infrastructure.exception.ErrorDetails;
import org.nthdimenzion.ddd.infrastructure.exception.ErrorMessageLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApplicationSetup implements ApplicationContextAware, IApplicationContext, ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationSetup.class);

    private static ConfigurableApplicationContext configurableApplicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ErrorMessageLocator errorMessageLocator = applicationContext.getBean("errorMessageLocator", ErrorMessageLocator.class);
        ErrorDetails.errorMessageLocator = errorMessageLocator;
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return configurableApplicationContext.getBean(name);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return configurableApplicationContext.getBean(requiredType);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return configurableApplicationContext.getBean(name, args);
    }

    @Override
    public boolean isProfile(String profile) {
        Environment environment = configurableApplicationContext.getEnvironment(); 
        String[] activeProfiles = environment.getActiveProfiles();
        return Lists.newArrayList(activeProfiles).contains(profile);
    }

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        try {
            logger.debug("Initializing spring profiles");
            this.configurableApplicationContext = configurableApplicationContext;
            configurableApplicationContext.getEnvironment().getPropertySources()
                    .addLast(new ResourcePropertySource("classpath:/org/application.properties"));
        } catch (IOException e) {
            logger.info("Unable to load fallback properties: " + e.getMessage());
        }
    }
}
