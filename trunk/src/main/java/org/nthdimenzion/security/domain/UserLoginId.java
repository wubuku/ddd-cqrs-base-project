package org.nthdimenzion.security.domain;

import com.google.common.base.Preconditions;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.nthdimenzion.ddd.domain.annotations.ValueObject;
import org.nthdimenzion.object.utils.EqualsFacilitator;
import org.springframework.util.ObjectUtils;

import javax.persistence.Embeddable;

@ValueObject
@Embeddable
public class UserLoginId {

    private String uid;

    public UserLoginId(final String uid) {
        Preconditions.checkNotNull(uid);
        this.uid = uid;
    }

    UserLoginId() {
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
        if (EqualsFacilitator.PROCEED_WITH_EQUALS == EqualsFacilitator.initialChecks(o, this)) {
            UserLoginId obj = (UserLoginId) o;
            return new EqualsBuilder().reflectionEquals(this, obj);
        }
        return EqualsFacilitator.initialChecks(o, this);
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(uid);
    }

}
