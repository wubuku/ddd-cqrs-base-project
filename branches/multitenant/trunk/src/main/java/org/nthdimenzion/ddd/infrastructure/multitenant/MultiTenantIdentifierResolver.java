package org.nthdimenzion.ddd.infrastructure.multitenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.nthdimenzion.security.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 21/1/13
 * Time: 10:11 PM
 */
public class MultiTenantIdentifierResolver implements CurrentTenantIdentifierResolver {


    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenantId = TenantIdHolder.getTenantId();
        System.out.println("   tenantId-->  " + tenantId);

        return tenantId;
    }

    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
