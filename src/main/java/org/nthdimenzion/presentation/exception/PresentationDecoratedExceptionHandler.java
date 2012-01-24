package org.nthdimenzion.presentation.exception;

import com.adamtaft.eb.EventHandler;
import com.google.common.eventbus.Subscribe;
import org.nthdimenzion.ddd.domain.annotations.EventHandlers;
import org.nthdimenzion.ddd.infrastructure.AbstractEventListener;
import org.nthdimenzion.ddd.infrastructure.IEventBus;
import org.nthdimenzion.ddd.infrastructure.exception.OperationFailed;
import org.nthdimenzion.presentation.infrastructure.IDisplayMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.zkoss.zk.ui.event.EventListener;

@EventHandlers
@Qualifier("presentationDecoratedExceptionHandler")
public class PresentationDecoratedExceptionHandler extends AbstractEventListener implements IExceptionHandler{

    private final Logger logger = LoggerFactory.getLogger(PresentationDecoratedExceptionHandler.class);

    private boolean isExceptionHandled = false;

    @Autowired
    @Qualifier("zkDisplayMessages")
    private IDisplayMessages displayMessages;

    public void setDisplayMessages(IDisplayMessages displayMessages) {
        this.displayMessages = displayMessages;
    }

    @Autowired
    public PresentationDecoratedExceptionHandler(@Qualifier("exceptionEventBus") IEventBus exceptionEventBus) {
        super(exceptionEventBus);
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
    public Boolean isExceptionHandled() {
        return new Boolean(isExceptionHandled);
    }
}

