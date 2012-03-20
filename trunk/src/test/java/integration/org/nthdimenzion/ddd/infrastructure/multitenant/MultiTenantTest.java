package org.nthdimenzion.ddd.infrastructure.multitenant;

import org.junit.Ignore;
import org.junit.Test;
import org.nthdimenzion.ddd.domain.ITenantAware;
import org.nthdimenzion.ddd.domain.multitenant.Tenant;
import org.nthdimenzion.testinfrastructure.AbstractTestFacilitator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@Ignore
public class MultiTenantTest extends AbstractTestFacilitator {

    @Autowired
    private UserDetailsService userService;

    @Autowired
    private ITenantAwareDataSourceFactory dataSourceFactory;


    @Test
    public void testDataSourceCreationForTenantOne() throws SQLException {
        ITenantAware tenantOne = new Tenant("001");

        Map<Object, Object> tenantIdToDataSourceMap = dataSourceFactory.fetchConfiguredTenantDataSources();
        DataSource dataSource = (DataSource) tenantIdToDataSourceMap.get(tenantOne.getTenantId());

        assertNotNull(tenantIdToDataSourceMap.get(tenantOne.getTenantId()));
        assertEquals("jdbc:mysql://localhost:3306/TenantOne", dataSource.getConnection().getMetaData().getURL());
        assertEquals("root@localhost", dataSource.getConnection().getMetaData().getUserName());

    }

    @Test
    public void testDataSourceCreationForTenantTwo() throws SQLException {
        ITenantAware tenantTwo = new Tenant("002");

        Map<Object, Object> tenantIdToDataSourceMap = dataSourceFactory.fetchConfiguredTenantDataSources();
        DataSource dataSource = (DataSource) tenantIdToDataSourceMap.get(tenantTwo.getTenantId());

        assertNotNull(dataSource);
        assertEquals("jdbc:mysql://localhost:3306/TenantTwo", dataSource.getConnection().getMetaData().getURL());
        assertEquals("root@localhost", dataSource.getConnection().getMetaData().getUserName());

        System.out.println("Thread.currentThread().getName() " + Thread.currentThread().getName());
    }

    /*@Test
    public void testGetUserDetailsFromTenantSchema() {
        System.out.println("Thread.currentThread().getName() " + Thread.currentThread().getName());
        TenantIdHolder.clearTenantId();
        ITenantAware currentTenant = new Tenant(JUNIT_TESTING_TENANT_ID);
        TenantIdHolder.setTenantId(currentTenant.getTenantId());

        UserDetails user = userService.loadUserByUsername("la");
        assertNotNull(user);
        assertEquals("la",user.getPassword());

        user = userService.loadUserByUsername("sa");
        assertNotNull(user);
        assertEquals("sa",user.getPassword());
    }
*/

    @Test
    public void testGetAllConfiguredTenantsDetails() {
        Map<Object, Object> tenantIdToDataSourceMap = dataSourceFactory.fetchConfiguredTenantDataSources();

        assertNotNull(tenantIdToDataSourceMap);
        assertEquals(5,tenantIdToDataSourceMap.size());

    }

}
