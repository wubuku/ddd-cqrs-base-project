package org.nthdimenzion.ddd.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.nthdimenzion.object.utils.EqualsFacilitator;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {


    private Long version = 1L;

    private LifeCycle lifeCycle;

    @Embedded
    public LifeCycle getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(LifeCycle lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    @Version
	public Long getVersion() {
	return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
