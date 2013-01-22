package org.nthdimenzion.ddd.infrastructure.multitenant;

import javax.sql.DataSource;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 22/1/13
 * Time: 1:22 PM
 */
public class DataSourceHolder {

    public final DataSource dataSource;
    public final String user;
    public final String password;

    public DataSourceHolder(DataSource dataSource, String user, String password) {
        this.dataSource = dataSource;
        this.user = user;
        this.password = password;
    }
}
