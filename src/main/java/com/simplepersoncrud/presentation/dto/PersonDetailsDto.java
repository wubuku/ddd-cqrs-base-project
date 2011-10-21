package com.simplepersoncrud.presentation.dto;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

public class PersonDetailsDto implements Serializable {
    public String name;
    public Long id;

    public PersonDetailsDto() {

    }

    public PersonDetailsDto(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof PersonDetailsDto) {
            PersonDetailsDto obj = (PersonDetailsDto) o;
            return EqualsBuilder.reflectionEquals(obj,this);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(new Object[]{name, id});
    }

    @Override
    public String toString() {
        return "PersonDetailsDto{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
