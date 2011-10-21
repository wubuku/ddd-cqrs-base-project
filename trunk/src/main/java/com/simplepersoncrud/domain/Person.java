package com.simplepersoncrud.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;

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
    @GeneratedValue(strategy= GenerationType.AUTO)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
         if(o instanceof Person){
            Person obj = (Person)o;
            return new EqualsBuilder().append(id,obj.id).isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(id);
    }
}
