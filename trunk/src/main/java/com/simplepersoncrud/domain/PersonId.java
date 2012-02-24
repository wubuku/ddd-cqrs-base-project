package com.simplepersoncrud.domain;

import com.google.common.base.Preconditions;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.hibernate.annotations.Immutable;
import org.nthdimenzion.ddd.domain.annotations.ValueObject;
import org.nthdimenzion.object.utils.EqualsFacilitator;
import org.springframework.util.ObjectUtils;

import javax.persistence.Embeddable;

@Embeddable
@ValueObject
@Immutable
public class PersonId {

    private String uid;

    public PersonId(final String uid){
        Preconditions.checkNotNull(uid);
        this.uid = uid;
    }

    PersonId(){

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
            PersonId obj = (PersonId)o;
            return new EqualsBuilder().append(uid,obj.uid).isEquals();
        }
        return EqualsFacilitator.initialChecksPass(o, this);
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(uid);
    }
}
