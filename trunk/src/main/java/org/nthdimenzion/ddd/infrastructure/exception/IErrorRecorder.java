package org.nthdimenzion.ddd.infrastructure.exception;

public interface IErrorRecorder {

    void persistFailedOperationDetails(OperationFailed operationFailed);

}
