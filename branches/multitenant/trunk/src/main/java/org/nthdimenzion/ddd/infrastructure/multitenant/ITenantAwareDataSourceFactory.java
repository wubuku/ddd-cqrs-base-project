package org.nthdimenzion.ddd.infrastructure.multitenant;

import org.nthdimenzion.ddd.domain.ITenantAware;
import org.nthdimenzion.ddd.domain.multitenant.TenantCustomisationDetails;

import javax.sql.DataSource;
import java.util.Map;

public interface ITenantAwareDataSourceFactory {

    Map<Object,Object> initialiseConfiguredTenantDataSources();

    Map<Object, Object>  fetchConfiguredTenantDataSources();
}
