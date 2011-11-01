package com.simplepersoncrud.domain;

import com.google.common.base.Preconditions;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.nthdimenzion.ddd.domain.annotations.ValueObject;
import org.nthdimenzion.object.utils.EqualsFacilitator;
import org.springframework.util.ObjectUtils;

import javax.persistence.Embeddable;

@ValueObject
@Embeddable
public class PersonId {

    private String uid;

    public PersonId(final String uid){
        Preconditions.checkNotNull(uid);
        this.uid = uid;
    }

    PersonId(){

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return uid;
    }

    @Override
    public boolean equals(Object o) {
    if (EqualsFacilitator.PROCEED_WITH_EQUALS == EqualsFacilitator.initialChecks(o, this)) {
            PersonId obj = (PersonId)o;
            return new EqualsBuilder().append(uid,obj.uid).isEquals();
        }
        return EqualsFacilitator.initialChecks(o, this);
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(uid);
    }
}
