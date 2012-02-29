package org.nthdimenzion.ddd.domain.multitenant;

public class TenantCustomisationDetails {
    private final Long customisationId;
    private final String jdbcUrl;
    private final String jdbcUsername;
    private final String jdbcPassword;

    public TenantCustomisationDetails(Long customisationId,String jdbcUrl, String jdbcUsername, String jdbcPassword) {
        this.jdbcUrl = jdbcUrl;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
        this.customisationId = customisationId;
    }

    public Long getCustomisationId() {
        return new Long(customisationId);
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getJdbcUsername() {
        return jdbcUsername;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }
}
