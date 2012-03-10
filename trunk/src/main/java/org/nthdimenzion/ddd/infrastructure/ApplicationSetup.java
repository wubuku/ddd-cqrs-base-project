package org.nthdimenzion.ddd.infrastructure;

import org.nthdimenzion.ddd.infrastructure.exception.ErrorDetails;
import org.nthdimenzion.ddd.infrastructure.exception.ErrorMessageLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApplicationSetup implements ApplicationContextAware , ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationSetup.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        ErrorMessageLocator errorMessageLocator = applicationContext.getBean("errorMessageLocator", ErrorMessageLocator.class);
        ErrorDetails.errorMessageLocator = errorMessageLocator;
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        try {
            logger.debug("Initializing spring profiles");
            applicationContext.getEnvironment().getPropertySources()
                    .addLast(new ResourcePropertySource("classpath:/org/application.properties"));
        } catch (IOException e) {
            logger.info("Unable to load fallback properties: " + e.getMessage());
        }
    }
}
