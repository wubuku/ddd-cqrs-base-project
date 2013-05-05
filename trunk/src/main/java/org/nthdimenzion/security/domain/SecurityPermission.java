package org.nthdimenzion.security.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.hibernate.annotations.Immutable;
import org.nthdimenzion.ddd.domain.IdGeneratingArcheType;
import org.nthdimenzion.ddd.domain.annotations.ValueObject;
import org.nthdimenzion.object.utils.EqualsFacilitator;
import org.springframework.util.ObjectUtils;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@ValueObject
@Entity
@Immutable
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"permissionId"})})
class SecurityPermission extends IdGeneratingArcheType {

    private String permissionId;
    private String description;

    SecurityPermission() {

    }

    public SecurityPermission(String permissionId,String description) {
        this.permissionId = permissionId;
        this.description = description;
    }

    public String getDescription() {
        return new String(description);
    }

    void setDescription(String description) {
        this.description = description;
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
        if (EqualsFacilitator.initialChecksPass(o, this)) {
            SecurityPermission obj = (SecurityPermission) o;
            return new EqualsBuilder().reflectionEquals(obj, this);
        }
        return EqualsFacilitator.initialChecksPass(o, this);
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(permissionId);
    }

}
