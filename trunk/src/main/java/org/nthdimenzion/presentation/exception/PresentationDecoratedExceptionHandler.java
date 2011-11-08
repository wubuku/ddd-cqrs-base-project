package org.nthdimenzion.presentation.exception;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.nthdimenzion.ddd.infrastructure.exception.ErrorDetails;
import org.nthdimenzion.presentation.infrastructure.IDisplayMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("presentationDecoratedExceptionHandler")
public class PresentationDecoratedExceptionHandler implements IExceptionHandler{

    private final Logger logger = LoggerFactory.getLogger(PresentationDecoratedExceptionHandler.class);

    private final EventBus exceptionEventBus = null;

    private boolean isExceptionHandled = false;

    private IDisplayMessages displayMessages;

    @Autowired
    public void setDisplayMessages(IDisplayMessages displayMessages) {
        this.displayMessages = displayMessages;
    }

    @Autowired
    @Qualifier("exceptionEventBus")
    public void PresentationDecoratedExceptionHandler(EventBus exceptionEventBus) {
        logger.debug("exceptionEventBus Injected into constructor " + exceptionEventBus);
        exceptionEventBus.register(this);
    }

    @Subscribe
    @Override
    public void exceptionHandler(ErrorDetails errorDetails){
        displayMessages.displayError(errorDetails.toString());
        logger.debug("Entered into exception handler " + errorDetails);
        isExceptionHandled = true;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        logger.debug("Un register from exceptionEventBus on finalize of PresentationDecoratedExceptionHandler");
        exceptionEventBus.unregister(this);
    }

    @Override
    public Boolean isExceptionHandled() {
        return new Boolean(isExceptionHandled);
    }
}

