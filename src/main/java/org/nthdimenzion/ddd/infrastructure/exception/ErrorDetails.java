package org.nthdimenzion.ddd.infrastructure.exception;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.nthdimenzion.object.utils.EqualsFacilitator;
import org.springframework.util.ObjectUtils;

public class ErrorDetails {

    private String errorCode = "001";
    private String errorMessage = "Operation Failed.Please contact administrator";
    private Throwable exception;
    private Object otherDetails;
    public static ErrorDetails UI_ERROR_DETAILS = new ErrorDetails("001","Operation Failed.Please contact administrator",null,null);

    public ErrorDetails(ErrorDetails errorDetails) {
        this.errorCode = errorDetails.errorCode;
        this.errorMessage = errorDetails.errorMessage;
        this.exception = errorDetails.exception;
        this.otherDetails = errorDetails.otherDetails;
    }

    public ErrorDetails(String errorCode, String errorMessage, Throwable exception, Object otherDetails) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.exception = exception;
        this.otherDetails = otherDetails;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    public Object getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(Object otherDetails) {
        this.otherDetails = otherDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (EqualsFacilitator.PROCEED_WITH_EQUALS == EqualsFacilitator.initialChecks(o, this)) {
            ErrorDetails obj = (ErrorDetails)o;
            return new EqualsBuilder().append(errorCode,obj.errorCode).isEquals();
        }
        return EqualsFacilitator.initialChecks(o, this);
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(errorCode);
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
