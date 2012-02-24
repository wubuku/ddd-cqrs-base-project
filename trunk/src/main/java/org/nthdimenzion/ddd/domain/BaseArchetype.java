package org.nthdimenzion.ddd.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class BaseArchetype implements IPersistable{


    private Long version = 1L;

    private LifeCycle lifeCycle = new LifeCycle();

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

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

    public void markAsArchived() {
        lifeCycle.markAsArchived();
    }

    public void markAsActive(){
        lifeCycle.markAsActive();
    }

}
