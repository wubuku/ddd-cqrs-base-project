package org.nthdimenzion.ddd.infrastructure.multitenant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

public class TenantRoutingDataSource extends AbstractRoutingDataSource {

    private final Map<Object, Object> tenantIdToDataSourceMap;
    private static final Logger logger = LoggerFactory.getLogger(TenantRoutingDataSource.class);

    public TenantRoutingDataSource(ITenantAwareDataSourceFactory tenantAwareDataSourceFactory){
        tenantIdToDataSourceMap = tenantAwareDataSourceFactory.fetchConfiguredTenantDataSources();
        setTargetDataSources(tenantIdToDataSourceMap);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        logger.debug("Tenant Id for Datasource is " + TenantIdHolder.getTenantId());
        return TenantIdHolder.getTenantId();
    }
}
