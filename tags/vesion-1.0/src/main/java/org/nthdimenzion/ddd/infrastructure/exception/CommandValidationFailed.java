package org.nthdimenzion.ddd.infrastructure.exception;

import org.nthdimenzion.ddd.domain.IEvent;

public class CommandValidationFailed implements IEvent{

    public String details = "Operation could not be completed";

}
