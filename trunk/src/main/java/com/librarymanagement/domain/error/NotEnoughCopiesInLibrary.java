package com.librarymanagement.domain.error;

import org.nthdimenzion.ddd.infrastructure.exception.ErrorDetails;
import org.nthdimenzion.ddd.infrastructure.exception.IBaseException;

public class NotEnoughCopiesInLibrary extends Exception implements IBaseException{
    private final ErrorDetails errorDetails;

    public NotEnoughCopiesInLibrary(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
    }

    @Override
    public ErrorDetails getErrorDetails() {
        return errorDetails;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String toString() {
        return errorDetails.toString();
    }
}
