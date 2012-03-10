package org.nthdimenzion.ddd.infrastructure.exception;

import org.nthdimenzion.ddd.domain.IEvent;

public class CommandValidationFailed implements IEvent{

    public String details = "Operation could not be completed";
    public final Throwable rootCause;
    
    public CommandValidationFailed(Throwable rootCause){
        this.rootCause = rootCause;
    }

    public CommandValidationFailed(String details, Throwable rootCause) {
        this(rootCause);
        this.details = details;
    }
}
