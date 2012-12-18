package org.nthdimenzion.ddd.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.nthdimenzion.object.utils.EqualsFacilitator;
import org.springframework.util.ObjectUtils;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class IdGeneratingArcheType extends BaseArchetype {

     private Long id;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
    if (EqualsFacilitator.initialChecksPass(o, this)) {
            IdGeneratingArcheType obj = (IdGeneratingArcheType)o;
            return new EqualsBuilder().append(id,obj.id).isEquals();
        }
        return EqualsFacilitator.initialChecksPass(o, this);
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
