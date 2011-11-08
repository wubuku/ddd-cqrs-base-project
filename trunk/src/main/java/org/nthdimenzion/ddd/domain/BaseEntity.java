package org.nthdimenzion.ddd.domain;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

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
