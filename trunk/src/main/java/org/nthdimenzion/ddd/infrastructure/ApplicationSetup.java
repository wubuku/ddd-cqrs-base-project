package org.nthdimenzion.ddd.infrastructure;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;
import org.nthdimenzion.ddd.domain.sharedkernel.MyBatisJodaDateTimeType;
import org.nthdimenzion.ddd.infrastructure.exception.ErrorDetails;
import org.nthdimenzion.ddd.infrastructure.exception.ErrorMessageLocator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationSetup implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        ErrorMessageLocator errorMessageLocator = applicationContext.getBean("errorMessageLocator", ErrorMessageLocator.class);
        ErrorDetails.errorMessageLocator = errorMessageLocator;

        /*SqlSessionFactory mybatisSessionFactory = applicationContext.getBean("sqlSessionFactory", SqlSessionFactory.class);
        MyBatisJodaDateTimeType myBatisJodaDateTimeType = applicationContext.getBean("myBatisJodaDateTimeType", MyBatisJodaDateTimeType.class);*/

        /*mybatisSessionFactory.getConfiguration().getTypeHandlerRegistry().register(DateTime.class, myBatisJodaDateTimeType);
        mybatisSessionFactory.getConfiguration().getTypeHandlerRegistry().register(DateTime.class, JdbcType.DATE, myBatisJodaDateTimeType);
        mybatisSessionFactory.getConfiguration().getTypeHandlerRegistry().register(DateTime.class, JdbcType.TIME, myBatisJodaDateTimeType);
        mybatisSessionFactory.getConfiguration().getTypeHandlerRegistry().register(DateTime.class, JdbcType.TIMESTAMP, myBatisJodaDateTimeType);*/
//        mybatisSessionFactory.getConfiguration().getTypeHandlerRegistry().register(DateTime.class, null, myBatisJodaDateTimeType);
    }
}
