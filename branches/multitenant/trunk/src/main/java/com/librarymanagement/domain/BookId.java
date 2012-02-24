package com.librarymanagement.domain;

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
public class BookId {

    private String uid;

    public BookId(final String uid){
        Preconditions.checkNotNull(uid);
        this.uid = uid;
    }

    BookId(){

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
            BookId obj = (BookId)o;
            return new EqualsBuilder().append(uid,obj.uid).isEquals();
        }
        return EqualsFacilitator.initialChecksPass(o, this);
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(uid);
    }
}
