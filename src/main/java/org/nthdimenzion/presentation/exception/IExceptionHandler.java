package org.nthdimenzion.presentation.exception;

import org.nthdimenzion.ddd.infrastructure.exception.ErrorDetails;
import org.nthdimenzion.ddd.infrastructure.exception.OperationFailed;

public interface IExceptionHandler {

    void failedOperationHandler(OperationFailed operationFailed);

    Boolean isExceptionHandled();
}
