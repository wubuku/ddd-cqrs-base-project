package org.nthdimenzion.presentation.infrastructure;

import org.nthdimenzion.object.utils.UtilValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
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

    public void redirectUsingResponse(String viewName, HttpServletResponse response) throws IOException {
        response.sendRedirect(viewResolver.resolveViewName(viewName));
    }

    public String findViewUrl(String viewName) {
        return viewResolver.resolveViewName(viewName);
    }

    public void redirect(String viewName) {
        logger.debug("Redirecting to " + viewName + " viewResolver.resolveViewName(viewName)  " + viewResolver.resolveViewName(viewName));
        redirect(viewName, Collections.<String, String>emptyMap());
    }

    public void redirect(String viewName, Map<String, String> args) {
        String url = buildUrl(viewName, args);
        Executions.getCurrent().sendRedirect(url);
    }

    String buildUrl(String viewName, Map<String, ?> args) {
        StringBuilder arguments = new StringBuilder();
        String url = viewResolver.resolveViewName(viewName);
        if (UtilValidator.isNotEmpty(args)) {
            for (Map.Entry<String, ?> entry : args.entrySet()) {
                arguments.append(entry.getKey());
                arguments.append("=");
                arguments.append(entry.getValue());
                arguments.append("&");
            }
            arguments.deleteCharAt(arguments.length() - 1);
            url = url.concat("?");
        }
        url = url.concat(arguments.toString());
        return url;
    }

    public void redirectToPopupOnEachClick(String viewName) {
        redirectToPopup(viewName, "_blank", Collections.<String, String>emptyMap());
    }

    public void redirectToPopupOnEachClick(String viewName,Map<String,String> args) {
        redirectToPopup(viewName, "_blank", args);
    }

    public void redirectToPopup(String viewName) {
        redirectToPopup(viewName, "_home", Collections.<String, String>emptyMap());
    }

    public void redirectToPopup(String viewName,Map<String,String> args) {
        redirectToPopup(viewName, "_home", args);
    }

    void redirectToPopup(String viewName,String target,Map<String,String> args) {
        String url = buildUrl(viewName,args);
        Executions.getCurrent().sendRedirect(url, target);
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