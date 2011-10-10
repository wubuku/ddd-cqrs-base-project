package org.nthdimenzion.tests;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@javax.persistence.Entity
@Table
public class Person {

    private Long id;
    private String name;
    private Long version;

    @Version
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
