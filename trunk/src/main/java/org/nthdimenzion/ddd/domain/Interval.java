/*
 * header file
 */
package org.nthdimenzion.ddd.domain;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.nthdimenzion.ddd.domain.annotations.ValueObject;
import org.springframework.util.ObjectUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
@ValueObject
public class Interval implements Serializable {

    private static final long serialVersionUID = 1L;

    private DateTime fromDate;

    private DateTime thruDate;

    private String completionComments;

    public static Interval start() {
        return new Interval();
    }

    public String getCompletionComments() {
        return completionComments;
    }

    public Interval() {
        fromDate = DateTime.now();
    }

    public Interval(DateTime fromDate, DateTime thruDate) {
        this.fromDate = fromDate;
        this.thruDate = thruDate;
    }

    public Interval(DateTime fromDate, DateTime thruDate, String completionComments) {
        this.fromDate = fromDate;
        this.thruDate = thruDate;
        this.completionComments = completionComments;
    }

    public Interval(DateTime fromDate) {
        this.fromDate = fromDate;
    }

    @Column
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    @NotNull
    DateTime getFromDate() {
        return fromDate;
    }

    @Column
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    DateTime getThruDate() {
        return thruDate;
    }

    void setCompletionComments(String completionComments) {
        this.completionComments = completionComments;
    }

    void setFromDate(DateTime fromDate) {
        this.fromDate = fromDate;
    }

    void setThruDate(DateTime thruDate) {
        this.thruDate = thruDate;
    }

    @Transient
    public boolean isCompleted() {
        return thruDate != null && thruDate.isBeforeNow();
    }

    public Interval complete(String comments) {
        return new Interval(fromDate, DateTime.now(), comments);
    }

    public void complete() {
        complete("No Remarks");
    }

    @Transient
    public boolean isInProgress() {
        return !isCompleted();
    }

    public Interval extendThruDateByDays(int days) {
        return new Interval(fromDate, thruDate.plusDays(days));
    }

    @Override
    public boolean equals(Object o) {
        return ObjectUtils.nullSafeEquals(o,this);
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(new Object[] {fromDate,thruDate,completionComments});
    }
}
