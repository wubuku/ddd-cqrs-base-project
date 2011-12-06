package org.nthdimenzion.ddd.infrastructure.exception;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.nthdimenzion.ddd.domain.IdGeneratingBaseEntity;
import org.nthdimenzion.ddd.domain.annotations.ValueObject;
import org.nthdimenzion.object.utils.EqualsFacilitator;
import org.nthdimenzion.object.utils.UtilValidator;
import org.springframework.util.ObjectUtils;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Entity
@ValueObject
public class ErrorDetails extends IdGeneratingBaseEntity implements Cloneable {

    private String errorCode = "001";
    private String errorMessage = "Operation Failed.Please contact administrator";
    private Throwable exception;
    private Boolean isShowErrorInView = Boolean.TRUE;
    public static IErrorMessageLocator errorMessageLocator;


    ErrorDetails() {

    }

    private ErrorDetails(ErrorDetails errorDetails) {
        this.errorCode = errorDetails.errorCode;
        this.errorMessage = errorDetails.errorMessage;
        this.exception = errorDetails.exception;
    }

    ErrorDetails(Builder builder) {
        this.errorCode = builder.errorCode;
        this.errorMessage = builder.errorMessage;
        this.exception = builder.exception;
        this.isShowErrorInView = builder.isShowErrorInView;
    }

    public String getErrorCode() {
        return new String(errorCode);
    }

    void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return new String(errorMessage);
    }

    void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Transient
    public Throwable getException() {
        return new Throwable(exception);
    }

    void setException(Throwable exception) {
        this.exception = exception;
    }

    public Boolean getShowErrorInView() {
        return new Boolean(isShowErrorInView);
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(new Object[]{errorCode,errorMessage,exception,isShowErrorInView});
    }

    @Override
    public String toString() {
        return errorMessage;
    }

    @Override
    public Object clone() {
        ErrorDetails errorDetails = new ErrorDetails(this);
        return errorDetails;
    }


    public static class Builder {
        private String errorCode = "001";
        private String errorMessage = "Operation Failed.Please contact administrator";
        private Throwable exception;
        public Boolean isShowErrorInView = Boolean.TRUE;
        private List<String> args;

        public Builder(String errorCode) {
            this.errorCode = errorCode;
        }

        public Builder exception(Throwable exception) {
            this.exception = exception;
            return this;
        }

        public Builder isShowErrorInView(Boolean isShowErrorInView) {
            this.isShowErrorInView = isShowErrorInView;
            return this;
        }

        public Builder args(List<String> args){
        this.args = args;
        return this;
        }

        public ErrorDetails build(){
            if(UtilValidator.isEmpty(args)){
                this.errorMessage = errorMessageLocator.getFormatterErrorMessageFor(errorCode);
            }
            else{
                this.errorMessage = errorMessageLocator.getFormatterErrorMessageFor(errorCode,args);
            }
            return new ErrorDetails(this);
        }

    }
}
