package org.nthdimenzion.presentation.exception;

import com.google.common.eventbus.Subscribe;
import org.nthdimenzion.ddd.domain.annotations.EventHandlers;
import org.nthdimenzion.ddd.infrastructure.AbstractEventListener;
import org.nthdimenzion.ddd.infrastructure.IEventBus;
import org.nthdimenzion.ddd.infrastructure.exception.CommandValidationFailed;
import org.nthdimenzion.ddd.infrastructure.exception.OperationFailed;
import org.nthdimenzion.presentation.infrastructure.IDisplayMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@EventHandlers
@Qualifier("presentationDecoratedExceptionHandler")
public class PresentationDecoratedExceptionHandler extends AbstractEventListener implements IExceptionHandler{

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

    @Subscribe
    public void failedCommandValidationHandler(CommandValidationFailed commandValidationFailed){
        displayMessages.displayError(commandValidationFailed.details);
        isExceptionHandled = true;
    }


    @Override
    public Boolean isExceptionHandled() {
        return new Boolean(isExceptionHandled);
    }
}

