package org.nthdimenzion.ddd.infrastructure.exception;

public interface IBaseException {

    ErrorDetails getErrorDetails();

    String toString();

}
