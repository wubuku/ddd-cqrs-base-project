package org.nthdimenzion.security.domain.error;

import org.nthdimenzion.ddd.infrastructure.exception.ErrorDetails;
import org.nthdimenzion.ddd.infrastructure.exception.IBaseException;

public class HomePageAlreadyExistsForUser extends Exception implements IBaseException{
    private final ErrorDetails errorDetails;

    public HomePageAlreadyExistsForUser(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
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
