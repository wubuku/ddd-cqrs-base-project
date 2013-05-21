package org.nthdimenzion.presentation.util;

import org.nthdimenzion.presentation.infrastructure.AbstractZKModel;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;

import javax.servlet.ServletContext;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 2/5/13
 * Time: 12:23 PM
 */
public class HeaderViewModel extends AbstractZKModel{

    @Command
    public void reload(){
        System.out.println("HeaderViewModel");
        ServletContext servletContext = (ServletContext) Executions.getCurrent().getDesktop().getWebApp().getNativeContext();

        XmlWebApplicationContext applicationContext = (XmlWebApplicationContext) WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

        ClassLoader  loader = this.getClass().getClassLoader();
        if (loader == null){
            loader = ClassLoader.getSystemClassLoader ();
        }
        InputStream in = loader.getResourceAsStream ("queryContext.xml");
        System.out.println(applicationContext.getClass().getName());
        System.out.println("applicationContext.containsBean -->>" + applicationContext.containsBean("ILibraryFinder"));

        Object sessionFactory = applicationContext.getBean("sessionFactory");

        applicationContext.refresh();

        ConfigurableBeanFactory configurableBeanFactory = applicationContext.getBeanFactory();
        configurableBeanFactory.destroyBean("sessionFactory",sessionFactory);
        configurableBeanFactory.registerSingleton("sessionFactory",sessionFactory);
    }
}
