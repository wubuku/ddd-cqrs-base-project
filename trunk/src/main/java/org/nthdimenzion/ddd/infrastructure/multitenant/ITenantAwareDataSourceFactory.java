package org.nthdimenzion.ddd.infrastructure.multitenant;

import java.util.Map;

public interface ITenantAwareDataSourceFactory {

    Map<Object,Object> initialiseConfiguredTenantDataSources();

    Map<Object, Object>  fetchConfiguredTenantDataSources();
}
