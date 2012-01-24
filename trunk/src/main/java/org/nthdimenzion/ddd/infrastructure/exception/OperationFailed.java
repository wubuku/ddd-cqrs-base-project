package org.nthdimenzion.ddd.infrastructure.exception;

import org.nthdimenzion.ddd.domain.IEvent;

public class OperationFailed implements IEvent{

    public ErrorDetails errorDetails;

    public OperationFailed(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
    }

    private OperationFailed(DisplayableException displayableException){
        this(displayableException.getErrorDetails());
    }

    public static OperationFailed createDefaultDisplayableException(Throwable throwable){
        DisplayableException displayableException = DisplayableException.DEFAULT_UI_EXCEPTION.havingCause(throwable);
        return new OperationFailed(displayableException);
    }
}
