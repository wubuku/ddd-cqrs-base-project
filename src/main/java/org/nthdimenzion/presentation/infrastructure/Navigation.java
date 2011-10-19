package org.nthdimenzion.presentation.infrastructure;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

public class Navigation {

    static final Logger logger = LoggerFactory.getLogger(Navigation.class);

    public static AbstractCachingViewResolver viewResolver = new ResourceBundleViewResolver();

    public void setViewResolver(AbstractCachingViewResolver viewResolver) {
        this.viewResolver = viewResolver;
    }

    public static void redirect(String viewName) {
	Executions.getCurrent().sendRedirect(viewResolver.resolveViewName(viewName));
	}

     public static void redirectToPopupOnEachClick(String viewName) {
	Executions.getCurrent().sendRedirect(viewResolver.resolveViewName(viewName),"_blank");
	}

    public static void redirectToPopup(String viewName) {
	Executions.getCurrent().sendRedirect(viewResolver.resolveViewName(viewName),"_home");
	}

    public static void navigateToDefaultContainer(String viewName, Map<?, ?> arguments) {
	navigate(viewName,arguments,(String)null);
	}

	public static void  navigateTo(String viewName, Map<?, ?> arguments, org.zkoss.zk.ui.Component container) {
	viewResolver.navigate(viewName, arguments,container);
	}


	public static boolean viewExists(String viewName){
	return viewResolver.viewExists(viewName);
	}

	public static void navigate(String viewName, Map<?, ?> arguments,String componentName) {
	viewResolver.navigate(viewName, arguments,componentName);
	}

	public static Window openModalWindow(String viewName, Map<?, ?> arguments){
	Window window = viewResolver.getWindowForViewName(viewName,arguments);
	try {
		window.doModal();
	} catch (Exception e) {
		throw new RuntimeException(e);
	}
	return window;
	}
}