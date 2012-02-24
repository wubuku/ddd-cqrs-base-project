package org.nthdimenzion.ddd.infrastructure.exception;

@Deprecated
public interface IErrorRecorder {

    void persistFailedOperationDetails(OperationFailed operationFailed);

}
