package org.nthdimenzion.presentation.infrastructure;

import com.google.common.base.Preconditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.nthdimenzion.cqrs.command.ICommand;
import org.nthdimenzion.cqrs.command.ICommandBus;
import org.nthdimenzion.crud.ICrud;
import org.nthdimenzion.ddd.infrastructure.IEventBus;
import org.nthdimenzion.ddd.infrastructure.exception.OperationFailed;
import org.nthdimenzion.object.utils.UtilMisc;
import org.nthdimenzion.object.utils.UtilValidator;
import org.nthdimenzion.presentation.exception.CommandConstraintException;
import org.nthdimenzion.security.domain.SystemUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.databind.TypeConverter;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AbstractZKModel {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private TypeConverter dateConverter = new JodaTimeToZkStringConverter();

    public TypeConverter getDateConverter() {
        return dateConverter;
    }


    @WireVariable("simpleCommandBus")
    @Qualifier("simpleCommandBus")
    protected ICommandBus commandBus;

    protected SystemUser loggedInUser;

    @WireVariable
    protected ICrud crudDao;

    @WireVariable("displayMessages")
    protected IDisplayMessages<EventListener> displayMessages;

    @WireVariable
    protected Navigation navigation;

    @WireVariable("exceptionEventBus")
    @Qualifier("exceptionEventBus")
    protected IEventBus exceptionEventBus;


    private final ModelMapper modelMapper = new ModelMapper();

    @WireVariable
    protected Validator validator;

    protected AbstractZKModel() {
        SystemUser systemUser = (SystemUser) Executions.getCurrent().getSession().getAttribute("loggedInUser");
        this.loggedInUser = systemUser;
        modelMapper.getConfiguration().enableFieldMatching(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }


    protected final Set<ConstraintViolation<ICommand>> validate(ICommand command){
        return validator.validate(command);
    }

    protected final Object sendCommand(ICommand command) {
        Preconditions.checkNotNull(command);
        return commandBus.send(command);
    }

    protected final boolean isSuccess(Object object) {
        if (object instanceof Boolean) {
            Boolean success = (Boolean) object;
            return success.booleanValue();
        }
        return object != null;
    }

    protected final Map createMapWithGivenKeys(String...keys){
        Map map = new HashMap();
        for(String key : keys){
            map.put(key,null);
        }
        return map;
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

    protected final <T> T getParam(String paramId) {
        logger.debug("ParamId " + paramId);
        T paramValue = (T) Executions.getCurrent().getParameter(paramId);
        if (paramValue == null) {
            paramValue = (T) Executions.getCurrent().getArg().get(paramId);
        }
        return paramValue;

    }

    protected final void raiseException(Throwable exception) {
        exceptionEventBus.raise(OperationFailed.createDefaultDisplayableException(exception));
    }

}
