package org.nthdimenzion.ddd.infrastructure.multitenant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

public class TenantRoutingDataSource extends AbstractRoutingDataSource {

    private final Map tenantIdToDataSourceMap;
    private static final Logger logger = LoggerFactory.getLogger(TenantRoutingDataSource.class);

    @Autowired
    public TenantRoutingDataSource(ITenantAwareDataSourceFactory tenantAwareDataSourceFactory){
        tenantIdToDataSourceMap = tenantAwareDataSourceFactory.fetchConfiguredTenantDataSource();
        setTargetDataSources(tenantIdToDataSourceMap);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        logger.debug("Tenant Id for Datasource is " + TenantIdHolder.getTenantId());
        return TenantIdHolder.getTenantId();
    }
}
