package org.nthdimenzion.ddd.infrastructure.multitenant;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.hibernate.cfg.Environment;
import org.hibernate.ejb.connection.InjectedDataSourceConnectionProvider;
import org.hibernate.service.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 21/1/13
 * Time: 11:08 PM
 */
public class MultiTenantConnectionProviderImpl extends AbstractMultiTenantConnectionProvider {

    Properties properties = new Properties();

    @Override
    protected ConnectionProvider getAnyConnectionProvider() {
        DataSourceHolder dataSourceHolder = DataSourceFactory.determineDataSourceForTenant(DataSourceFactory.DEFAULT_TENANT);
        properties.setProperty(Environment.USER, dataSourceHolder.user);
        properties.setProperty(Environment.PASS,dataSourceHolder.password);
        InjectedDataSourceConnectionProvider connectionProvider = new InjectedDataSourceConnectionProvider();
        connectionProvider.setDataSource(dataSourceHolder.dataSource);
        connectionProvider.configure(properties);
        return connectionProvider;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected ConnectionProvider selectConnectionProvider(String tenantIdentifier) {
        DataSourceHolder dataSourceHolder = DataSourceFactory.determineDataSourceForTenant(tenantIdentifier);
        properties.setProperty(Environment.USER,dataSourceHolder.user);
        properties.setProperty(Environment.PASS,dataSourceHolder.password);
        InjectedDataSourceConnectionProvider connectionProvider = new InjectedDataSourceConnectionProvider();
        connectionProvider.setDataSource(dataSourceHolder.dataSource);
        connectionProvider.configure(properties);
        return connectionProvider;
    }
}
