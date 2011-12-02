package org.nthdimenzion.ddd.infrastructure.exception;

import org.nthdimenzion.ddd.domain.IEvent;

public class OperationFailed implements IEvent{

    public ErrorDetails errorDetails;

    public OperationFailed(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
    }
}
