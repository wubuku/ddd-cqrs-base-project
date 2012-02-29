package org.nthdimenzion.ddd.infrastructure.multitenant;

import org.nthdimenzion.ddd.domain.ITenantAware;
import org.nthdimenzion.ddd.domain.multitenant.TenantCustomisationDetails;

import javax.sql.DataSource;
import java.util.Map;

public interface ITenantAwareDataSourceFactory {

    public static ITenantAwareDataSourceFactory INSTANCE = new DataSourceFactory();
    
    DataSource getCommonSchemaDataSource();

    Map<Object,Object> initialiseConfiguredTenantDataSources();

    Map<Object, Object>  fetchConfiguredTenantDataSources();
}
