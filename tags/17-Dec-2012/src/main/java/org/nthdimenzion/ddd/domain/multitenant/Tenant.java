package org.nthdimenzion.ddd.domain.multitenant;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.nthdimenzion.ddd.domain.ITenantAware;
import org.nthdimenzion.object.utils.EqualsFacilitator;
import org.springframework.util.ObjectUtils;

public class Tenant implements ITenantAware{
    
    private final String tenantId;
    private String tenantName;
    private Boolean isEnabled = Boolean.TRUE;
    private Long customisationId;

    public Tenant(String tenantId) {
        this.tenantId = tenantId;
    }

    public Tenant(String tenantId, String tenantName) {
        this(tenantId);
        this.tenantName = tenantName;
    }

    public Tenant(String tenantId, String tenantName,Boolean isEnabled) {
        this(tenantId,tenantName);
        this.isEnabled = isEnabled;
    }


    public Tenant with(Long customisationId){
        this.customisationId = customisationId;
        return this;
    }

    public String getTenantId() {
        return tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public Boolean getEnabled() {
        return new Boolean(isEnabled);
    }

    public void disable(){
        isEnabled = Boolean.FALSE;
    }

    public void enable(){
        isEnabled = Boolean.TRUE;
    }

    @Override
    public String toString() {
        return "Tenant Id" + tenantId;
    }

    @Override
    public boolean equals(Object o) {
        if (EqualsFacilitator.initialChecksPass(o, this)) {
            Tenant obj = (Tenant)o;
            return new EqualsBuilder().append(tenantId,obj.tenantId).isEquals();
        }
        return EqualsFacilitator.initialChecksPass(o, this);
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(tenantId);
    }
}
