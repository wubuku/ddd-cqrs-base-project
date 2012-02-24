package org.nthdimenzion.ddd.infrastructure.exception;

import org.apache.commons.lang.ObjectUtils;
import org.nthdimenzion.object.utils.UtilValidator;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class ErrorMessageLocator implements IErrorMessageLocator{

     private Properties properties = new Properties();

    public void setExceptionMessageHolder(String basename) {
	ClassLoader bundleClassLoader = Thread.currentThread().getContextClassLoader();
	try {
		properties.load(bundleClassLoader.getResourceAsStream(basename));
	} catch (IOException e) {
		throw new RuntimeException(e);
	}
	}

	public void setExceptionMessageHolders(String[] basenames) {
	for(String basename : basenames)
		setExceptionMessageHolder(basename);
    }

    public String getFormatterErrorMessageFor(String errorCode, List<String> args){
        String formattedErrorMessage = null;
        if(UtilValidator.isEmpty(args))
            formattedErrorMessage = properties.getProperty(errorCode);
        else{
            String errorMessage = properties.getProperty(errorCode);
            formattedErrorMessage = String.format(errorMessage,args.toArray());
        }
        return formattedErrorMessage;
    }

    public String getFormatterErrorMessageFor(String errorCode){
        return getFormatterErrorMessageFor(errorCode, null);
    }

    @Override
    public boolean equals(Object o) {
        return ObjectUtils.equals(this,o);
    }

    @Override
    public int hashCode() {
        return ObjectUtils.hashCode(properties);
    }
}
