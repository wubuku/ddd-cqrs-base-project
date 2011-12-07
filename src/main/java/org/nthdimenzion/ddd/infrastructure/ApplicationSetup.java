package org.nthdimenzion.ddd.infrastructure;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.joda.money.CurrencyUnit;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.nthdimenzion.ddd.domain.sharedkernel.EncryptedBigDecimalType;
import org.nthdimenzion.ddd.infrastructure.exception.ErrorDetails;
import org.nthdimenzion.ddd.infrastructure.exception.ErrorMessageLocator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ApplicationSetup implements ApplicationContextAware{

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        ErrorMessageLocator errorMessageLocator = applicationContext.getBean("errorMessageLocator", ErrorMessageLocator.class);
        ErrorDetails.errorMessageLocator = errorMessageLocator;
        SqlSessionFactory mybatisSessionFactory = applicationContext.getBean("sqlSessionFactory",SqlSessionFactory.class);
        EncryptedBigDecimalType encryptedBigDecimalType = applicationContext.getBean("encryptedBigDecimalType",EncryptedBigDecimalType.class);
        mybatisSessionFactory.getConfiguration().getTypeHandlerRegistry().register(BigDecimal.class, encryptedBigDecimalType);

        mybatisSessionFactory.getConfiguration().getTypeHandlerRegistry().register(JdbcType.DECIMAL, encryptedBigDecimalType);
        mybatisSessionFactory.getConfiguration().getTypeHandlerRegistry().register(JdbcType.NUMERIC, encryptedBigDecimalType);
        mybatisSessionFactory.getConfiguration().getTypeHandlerRegistry().register(JdbcType.REAL, encryptedBigDecimalType);
        mybatisSessionFactory.getConfiguration().getTypeHandlerRegistry().register(BigDecimal.class,null, encryptedBigDecimalType);

    }
}
