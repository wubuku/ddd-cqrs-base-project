package com.librarymanagement.presentation.dto;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.joda.time.DateTime;
import org.nthdimenzion.object.utils.EqualsFacilitator;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.math.BigDecimal;

public class BookDetailsDto implements Serializable {
    public BigDecimal amount;
    public String currency_code;
    public String name;
    public Long id;
    public DateTime  fromDate;

    public BookDetailsDto() {

    }

    public BookDetailsDto(String name, Long id) {
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public DateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(DateTime fromDate) {
        this.fromDate = fromDate;
    }

    @Override
    public boolean equals(Object o) {
        if (EqualsFacilitator.initialChecksPass(o, this)) {
            return EqualsBuilder.reflectionEquals(o,this) ;
        }
        return EqualsFacilitator.initialChecksPass(o, this);
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
