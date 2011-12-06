package org.nthdimenzion.presentation.exception;

import com.google.common.eventbus.Subscribe;
import org.nthdimenzion.ddd.infrastructure.IEventBus;
import org.nthdimenzion.ddd.infrastructure.exception.OperationFailed;
import org.nthdimenzion.presentation.infrastructure.IDisplayMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("presentationDecoratedExceptionHandler")
public class PresentationDecoratedExceptionHandler implements IExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(PresentationDecoratedExceptionHandler.class);

    private final IEventBus exceptionEventBus;

    private boolean isExceptionHandled = false;

    @Autowired
    @Qualifier("zkDisplayMessages")
    private IDisplayMessages displayMessages;

    public void setDisplayMessages(IDisplayMessages displayMessages) {
        this.displayMessages = displayMessages;
    }

    @Autowired
    public PresentationDecoratedExceptionHandler(@Qualifier("exceptionEventBus") IEventBus exceptionEventBus) {
        this.exceptionEventBus = exceptionEventBus;
        logger.debug("exceptionEventBus Injected into constructor " + exceptionEventBus);
        exceptionEventBus.register(this);
    }

    @Subscribe
    @Override
    public void failedOperationHandler(OperationFailed operationFailed) {
        if (operationFailed.errorDetails.getShowErrorInView()) {
            displayMessages.displayError(operationFailed.errorDetails.toString());
            isExceptionHandled = true;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        logger.debug("Un register from exceptionEventBus on finalize of PresentationDecoratedExceptionHandler");
        exceptionEventBus.unRegister(this);
    }

    @Override
    public Boolean isExceptionHandled() {
        return new Boolean(isExceptionHandled);
    }
}

