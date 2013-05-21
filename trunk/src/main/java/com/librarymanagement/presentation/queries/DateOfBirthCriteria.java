package com.librarymanagement.presentation.queries;

import org.joda.time.DateTime;
import org.nthdimenzion.presentation.ICriteria;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 25/4/13
 * Time: 6:02 PM
 */
public class DateOfBirthCriteria implements ICriteria{

    private DateTime startDate;
    private DateTime endDate;

    public DateOfBirthCriteria(DateTime startDate, DateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }
}
