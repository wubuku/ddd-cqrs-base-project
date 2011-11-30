package com.librarymanagement.domain;

import org.nthdimenzion.ddd.domain.IdGeneratingBaseEntity;

import javax.persistence.Entity;

@Entity
public class LibraryConfig extends IdGeneratingBaseEntity{
    private String name;
    private Integer maxLendingPeriod;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxLendingPeriod() {
        return maxLendingPeriod;
    }

    public void setMaxLendingPeriod(Integer maxLendingPeriod) {
        this.maxLendingPeriod = maxLendingPeriod;
    }
}
