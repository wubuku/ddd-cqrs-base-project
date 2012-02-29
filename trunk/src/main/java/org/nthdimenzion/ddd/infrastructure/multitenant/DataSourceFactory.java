package org.nthdimenzion.ddd.infrastructure.multitenant;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.nthdimenzion.ddd.domain.ITenantAware;
import org.nthdimenzion.ddd.domain.multitenant.Tenant;
import org.nthdimenzion.ddd.domain.multitenant.TenantCustomisationDetails;
import org.nthdimenzion.object.utils.UtilValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DataSourceFactory extends NamedParameterJdbcDaoSupport implements ITenantAwareDataSourceFactory {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceFactory.class);
    Map<Object, Object> tenantIdToDataSourceMap = null;

    DataSource createDataSource(ITenantAware tenant) {
        logger.debug("Going to create a brand new datasource for tenant " + tenant.getTenantId());
        Preconditions.checkNotNull(tenant);
        MysqlDataSource dataSource = new MysqlDataSource();
        TenantCustomisationDetails customisationDetails = getCustomisationDetails(tenant);
        dataSource.setUrl(customisationDetails.getJdbcUrl());
        dataSource.setUser(customisationDetails.getJdbcUsername());
        dataSource.setPassword(customisationDetails.getJdbcPassword());
        return dataSource;
    }

    TenantCustomisationDetails getCustomisationDetails(ITenantAware tenant) {
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        Map<String, Object> tenantResult = jdbcTemplate.queryForMap("select * from tenant where tenantid = ?", tenant.getTenantId());
        Map<String, Object> customisationResult = jdbcTemplate.queryForMap("select * from tenant_customisation_details where customisationId = ?", tenantResult.get("customisationId"));
        TenantCustomisationDetails tenantCustomisationDetails = new TenantCustomisationDetails((Long) customisationResult.get("customisationId"), (String) customisationResult.get("jdbcUrl"), (String) customisationResult.get("jdbcUsername"), (String) customisationResult.get("jdbcPassword"));
        return tenantCustomisationDetails;
    }

    @Override
    public Map<Object, Object> initialiseConfiguredTenantDataSources() {
        Map<Object, Object> tenantIdToDataSourceMap = Maps.newHashMap();
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        List<Tenant> allConfiguredTenants = jdbcTemplate.query("select * from tenant", new RowMapper<Tenant>() {
            @Override
            public Tenant mapRow(ResultSet rs, int rowNum) throws SQLException {
                Tenant tenant = new Tenant(rs.getString("tenantId"), rs.getString("tenantName"), rs.getBoolean("isEnabled"));
                return tenant.with(rs.getLong("customisationId"));
            }
        });
        for (Tenant tenant : allConfiguredTenants) {
            tenantIdToDataSourceMap.put(tenant.getTenantId(), createDataSource(tenant));
        }
        this.tenantIdToDataSourceMap = tenantIdToDataSourceMap;
        return tenantIdToDataSourceMap;
    }

    @Override
    public Map<Object, Object> fetchConfiguredTenantDataSources() {
        if (UtilValidator.isNotEmpty(this.tenantIdToDataSourceMap)) {
            logger.debug("Picking data from cache");
            return tenantIdToDataSourceMap;
        } else {
            logger.debug("initialiseConfiguredTenantDataSources");
            return initialiseConfiguredTenantDataSources();
        }
    }


}
