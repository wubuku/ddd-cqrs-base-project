package org.nthdimenzion.tests;

import javax.persistence.Id;
import javax.persistence.Table;

@javax.persistence.Entity
@Table
public class Person {

    private Long id;
    private String name;

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
