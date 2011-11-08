package org.nthdimenzion.ddd.domain;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class BaseEntity {


    private Long version = 1L;

    private LifeCycle lifeCycle;

    @Embedded
    protected LifeCycle getLifeCycle() {
        return lifeCycle;
    }

    protected void setLifeCycle(LifeCycle lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    @Version
	protected Long getVersion() {
	return version;
    }

    protected void setVersion(Long version) {
        this.version = version;
    }

}
