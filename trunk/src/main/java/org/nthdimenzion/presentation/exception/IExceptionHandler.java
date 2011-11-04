package org.nthdimenzion.presentation.exception;

import org.nthdimenzion.ddd.infrastructure.exception.ErrorDetails;

public interface IExceptionHandler {

    void exceptionHandler(ErrorDetails errorDetails);

    Boolean isExceptionHandled();
}
