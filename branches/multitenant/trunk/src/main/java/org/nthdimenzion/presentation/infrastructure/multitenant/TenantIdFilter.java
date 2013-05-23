package org.nthdimenzion.presentation.infrastructure.multitenant;

import com.google.common.base.Function;
import org.apache.commons.lang.ObjectUtils;
import org.nthdimenzion.ddd.infrastructure.multitenant.TenantIdHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public final class TenantIdFilter implements Filter {

    private static final String
            SPRING_SECURITY_CHECK_MAPPING = "/j_spring_security_check";

    private static final String
            SPRING_SECURITY_LOGOUT_MAPPING = "/j_spring_security_logout";

    private static final String TENANT_HTTP_KEY = "tenantId";

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private FilterConfig filterConfig;

    private Function<String,String> tenantIdExtractor;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        tenantIdExtractor = new UrlBasedTenantIdExtractor();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (null == filterConfig) {
            return;
        }
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Clear tenant security context holder, and if it's a logout
        // request then clear tenant attribute from the session
        TenantIdHolder.clearTenantId();
        if (httpRequest.getRequestURI().endsWith(SPRING_SECURITY_LOGOUT_MAPPING)) {
            httpRequest.getSession().removeAttribute(TENANT_HTTP_KEY);
        }

        // Resolve Tenant ID
        String tenantId = null;
        if (httpRequest.getRequestURI().endsWith(SPRING_SECURITY_CHECK_MAPPING)) {
            tenantId = extractTenantId(httpRequest);
            System.out.println(tenantId);
            httpRequest.getSession().setAttribute(TENANT_HTTP_KEY, tenantId);
        } else {
            tenantId = (String) httpRequest.getSession().getAttribute(TENANT_HTTP_KEY);
        }

        // If found, set the Tenant ID in the security context
        /*tenantId = "001";*/
        if (null != tenantId) {
            TenantIdHolder.setTenantId(tenantId);
            logger.info("Tenant context set with Tenant ID: " + tenantId);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }



    public void setTenantIdExtractor(Function<String, String> tenantIdExtractor) {
        this.tenantIdExtractor = tenantIdExtractor;
    }

    protected String extractTenantId(HttpServletRequest request){
        if(tenantIdExtractor == null){
            return  request.getParameter(TENANT_HTTP_KEY);
        }
        else{

            return (String)ObjectUtils.defaultIfNull(tenantIdExtractor.apply(request.getHeader("Host")),request.getParameter(TENANT_HTTP_KEY));
        }

    }
}


