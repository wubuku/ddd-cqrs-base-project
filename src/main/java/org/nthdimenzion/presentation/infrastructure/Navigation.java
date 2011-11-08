package org.nthdimenzion.presentation.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.IOException;
import java.util.Map;

@Component
public class Navigation {

    private static final Logger logger = LoggerFactory.getLogger(Navigation.class);

    public final AbstractCachingViewResolver viewResolver;

    @Autowired
    public Navigation(AbstractCachingViewResolver viewResolver) {
        this.viewResolver = viewResolver;
        logger.debug("Inject dependencies");
    }

    public void redirectUsingResponse(String viewName,HttpServletResponse response ) throws IOException {
    response.sendRedirect(viewResolver.resolveViewName(viewName));
    }

    public String findViewUrl(String viewName){
        return viewResolver.resolveViewName(viewName);
    }

    public void redirect(String viewName) {
        logger.debug("Redirecting to " + viewName + " viewResolver.resolveViewName(viewName)  " + viewResolver.resolveViewName(viewName));
        Executions.getCurrent().sendRedirect(viewResolver.resolveViewName(viewName));
    }

    public void redirectToPopupOnEachClick(String viewName) {
        Executions.getCurrent().sendRedirect(viewResolver.resolveViewName(viewName), "_blank");
    }

    public void redirectToPopup(String viewName) {
        Executions.getCurrent().sendRedirect(viewResolver.resolveViewName(viewName), "_home");
    }

    public void navigateToDefaultContainer(String viewName, Map<?, ?> arguments) {
        navigate(viewName, arguments, (String) null);
    }

    public void navigateTo(String viewName, Map<?, ?> arguments, org.zkoss.zk.ui.Component container) {
        viewResolver.navigate(viewName, arguments, container);
    }


    public boolean viewExists(String viewName) {
        return viewResolver.viewExists(viewName);
    }

    public void navigate(String viewName, Map<?, ?> arguments, String componentName) {
        viewResolver.navigate(viewName, arguments, componentName);
    }

    public Window openModalWindow(String viewName, Map<?, ?> arguments) {
        Window window = viewResolver.getWindowForViewName(viewName, arguments);
        try {
            window.doModal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return window;
    }
}