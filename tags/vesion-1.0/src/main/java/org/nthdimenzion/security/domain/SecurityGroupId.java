package org.nthdimenzion.security.domain;

import com.google.common.base.Preconditions;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.nthdimenzion.ddd.domain.annotations.ValueObject;
import org.nthdimenzion.object.utils.EqualsFacilitator;
import org.springframework.util.ObjectUtils;

import javax.persistence.Embeddable;

@ValueObject
@Embeddable
public class SecurityGroupId {
    private String uid;

    public SecurityGroupId(final String uid) {
        Preconditions.checkNotNull(uid);
        this.uid = uid;
    }

    SecurityGroupId() {

    }

    public String getUid() {
        return new String(uid);
    }

    void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return new String(uid);
    }

    @Override
    public boolean equals(Object o) {
        if (EqualsFacilitator.initialChecksPass(o, this)) {
            SecurityGroupId obj = (SecurityGroupId) o;
            return new EqualsBuilder().append(uid, obj.uid).isEquals();
        }
        return EqualsFacilitator.initialChecksPass(o, this);
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(uid);
    }

}
