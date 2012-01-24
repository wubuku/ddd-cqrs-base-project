package org.nthdimenzion.presentation.infrastructure;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.nthdimenzion.cqrs.command.ICommand;
import org.nthdimenzion.cqrs.command.ICommandBus;
import org.nthdimenzion.presentation.annotations.Composer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;

@Composer
public class AbstractZkComposer extends GenericForwardComposer{

     protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("simpleCommandBus")
    protected ICommandBus commandBus;

    @Autowired
    protected IDisplayMessages<EventListener> displayMessages;

    @Autowired
    protected Navigation navigation;

    protected ModelMapper modelMapper = new ModelMapper();


    protected AbstractZkComposer(){
        modelMapper.getConfiguration().enableFieldMatching(true)
                                      .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);


    }

    protected Object sendCommand(ICommand command){
        return commandBus.send(command);
    }

    protected boolean isSuccess(Object object){
        if(object instanceof Boolean){
            Boolean success = (Boolean)object;
            return success.booleanValue();
        }
        return object !=null;
    }
}
