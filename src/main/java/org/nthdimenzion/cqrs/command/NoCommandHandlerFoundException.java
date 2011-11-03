package org.nthdimenzion.cqrs.command;

import org.nthdimenzion.ddd.infrastructure.exception.ErrorDetails;
import org.nthdimenzion.ddd.infrastructure.exception.IBaseException;

public class NoCommandHandlerFoundException extends RuntimeException implements IBaseException{
    private ErrorDetails errorDetails;

    public NoCommandHandlerFoundException(String commandName){
        errorDetails = new ErrorDetails("002","No CommandHandler Found for Command of Type " + commandName);
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
