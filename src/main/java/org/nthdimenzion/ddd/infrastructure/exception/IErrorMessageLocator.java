package org.nthdimenzion.ddd.infrastructure.exception;

import org.nthdimenzion.object.utils.UtilValidator;

import java.util.List;

public interface IErrorMessageLocator {

    String getFormatterErrorMessageFor(String errorCode, List<String> args);

    String getFormatterErrorMessageFor(String errorCode);
}
