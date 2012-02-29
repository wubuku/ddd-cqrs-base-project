package org.nthdimenzion.presentation.infrastructure.multitenant;

import org.nthdimenzion.ddd.infrastructure.exception.ErrorDetails;
import org.nthdimenzion.ddd.infrastructure.exception.IBaseException;

public class InvalidLoggedInTenantException extends Exception implements IBaseException {
    private ErrorDetails errorDetails;

    public InvalidLoggedInTenantException(){
        errorDetails = new ErrorDetails.Builder("004").build();
    }

    @Override
    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }

    @Override
    public String toString() {
        return errorDetails.toString();
    }
}
