package org.nthdimenzion.ddd.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.nthdimenzion.object.utils.EqualsFacilitator;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;

@MappedSuperclass
public abstract class IdGeneratingBaseEntity extends BaseEntity{

     private Long id;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
    if (EqualsFacilitator.PROCEED_WITH_EQUALS == EqualsFacilitator.initialChecks(o, this)) {
            IdGeneratingBaseEntity obj = (IdGeneratingBaseEntity)o;
            return new EqualsBuilder().append(id,obj.id).isEquals();
        }
        return EqualsFacilitator.initialChecks(o, this);
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(id);
    }

    @Override
    public String toString() {
        return "Id " + id;
    }
}
