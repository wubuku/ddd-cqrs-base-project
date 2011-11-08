package org.nthdimenzion.security.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.nthdimenzion.ddd.domain.IdGeneratingBaseEntity;
import org.nthdimenzion.ddd.domain.annotations.ValueObject;
import org.nthdimenzion.object.utils.EqualsFacilitator;
import org.springframework.util.ObjectUtils;

import javax.persistence.Entity;

@ValueObject
@Entity
public class SecurityPermission extends IdGeneratingBaseEntity {

    private String permissionId;

    SecurityPermission() {

    }

    public SecurityPermission(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionId() {
        return new String(permissionId);
    }

    void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return new String(permissionId);
    }

    @Override
    public boolean equals(Object o) {
        if (EqualsFacilitator.PROCEED_WITH_EQUALS == EqualsFacilitator.initialChecks(o, this)) {
            SecurityPermission obj = (SecurityPermission) o;
            return new EqualsBuilder().reflectionEquals(obj, this);
        }
        return EqualsFacilitator.initialChecks(o, this);
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(permissionId);
    }

}
