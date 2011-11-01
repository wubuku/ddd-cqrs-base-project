package org.nthdimenzion.cqrs.command;

import org.nthdimenzion.ddd.infrastructure.exception.ErrorDetails;
import org.nthdimenzion.ddd.infrastructure.exception.IBaseException;

public class NoCommandHandlerFoundException extends RuntimeException implements IBaseException{
    private ErrorDetails errorDetails;

    public NoCommandHandlerFoundException(ErrorDetails errorDetails){
        super(errorDetails.getErrorMessage());
        this.errorDetails = errorDetails;
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
