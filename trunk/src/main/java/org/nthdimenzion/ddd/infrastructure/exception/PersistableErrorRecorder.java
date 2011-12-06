package org.nthdimenzion.ddd.infrastructure.exception;

import com.google.common.eventbus.Subscribe;
import org.nthdimenzion.ddd.infrastructure.IEventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class PersistableErrorRecorder implements IErrorRecorder {

    private IErrorDetailsRepository errorDetailsRepository;
    private final IEventBus exceptionEventBus;
     private final Logger logger = LoggerFactory.getLogger(PersistableErrorRecorder.class);

    @Autowired
    public PersistableErrorRecorder(IErrorDetailsRepository errorDetailsRepository, @Qualifier("exceptionEventBus") IEventBus exceptionEventBus) {
        this.errorDetailsRepository = errorDetailsRepository;
        this.exceptionEventBus = exceptionEventBus;
        exceptionEventBus.register(this);
    }

    @Override
    @Subscribe
    public void persistFailedOperationDetails(OperationFailed operationFailed) {
        ErrorDetails errorDetails = operationFailed.errorDetails;
        errorDetails = errorDetailsRepository.save(errorDetails);
        logger.debug("Persisting error details");
    }

     @Override
    protected void finalize() throws Throwable {
        super.finalize();
        exceptionEventBus.unRegister(this);
    }
}
