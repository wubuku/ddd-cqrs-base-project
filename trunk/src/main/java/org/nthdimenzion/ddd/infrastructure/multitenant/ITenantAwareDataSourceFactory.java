package org.nthdimenzion.ddd.infrastructure.multitenant;

import javax.sql.DataSource;
import java.util.Map;

public interface ITenantAwareDataSourceFactory {

    Map<Object,DataSourceHolder> initialiseConfiguredTenantDataSources();

    Map<Object, DataSourceHolder> fetchConfiguredTenantDataSourceHolders();

    Map<Object,DataSource> fetchConfiguredTenantDataSource();
}
