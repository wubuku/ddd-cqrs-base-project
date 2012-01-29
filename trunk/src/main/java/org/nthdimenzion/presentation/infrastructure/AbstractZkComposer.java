package org.nthdimenzion.presentation.infrastructure;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.nthdimenzion.cqrs.command.ICommand;
import org.nthdimenzion.cqrs.command.ICommandBus;
import org.nthdimenzion.ddd.infrastructure.IEventBus;
import org.nthdimenzion.ddd.infrastructure.exception.CommandValidationFailed;
import org.nthdimenzion.object.utils.UtilMisc;
import org.nthdimenzion.object.utils.UtilValidator;
import org.nthdimenzion.presentation.annotations.Composer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;

@Composer
public abstract class AbstractZkComposer extends GenericForwardComposer {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("simpleCommandBus")
    protected ICommandBus commandBus;

    @Autowired
    protected IDisplayMessages<EventListener> displayMessages;

    @Autowired
    protected Navigation navigation;

    private final ModelMapper modelMapper = new ModelMapper();

    protected AbstractZkComposer() {
        modelMapper.getConfiguration().enableFieldMatching(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    protected final Object sendCommand(ICommand command) {
        if (command == null)
            return command;
        return commandBus.send(command);
    }

    protected final boolean isSuccess(Object object) {
        if (object instanceof Boolean) {
            Boolean success = (Boolean) object;
            return success.booleanValue();
        }
        return object != null;
    }

    protected final <D, S> D populate(S source, D destination) {
        return UtilMisc.populate(source, destination, modelMapper);
    }

    /**
     * @param source
     * @param clazz
     * @param <D>
     * @param <S>
     * @return instance of clazz
     *         <p/>
     *         Ensure destination class has a public no arg constructor
     */
    public final <D, S> D populate(S source, Class<D> clazz) {
        return UtilMisc.populate(source, clazz, modelMapper);
    }

    protected final String getParam(String paramId) {
        logger.debug("ParamId " + paramId);
        return Executions.getCurrent().getParameter(paramId);

    }
}
