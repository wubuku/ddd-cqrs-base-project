package org.nthdimenzion.cqrs.command;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.nthdimenzion.ddd.infrastructure.IEventBus;
import org.nthdimenzion.object.utils.UtilMisc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class AbstractCommandHandler {

    private ModelMapper modelMapper = new ModelMapper();
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected final boolean success = true;
    protected final boolean failure = false;

    @Autowired
    @Qualifier("applicationEventBus")
    protected IEventBus applicationEventBus;

    protected AbstractCommandHandler(){
        modelMapper.getConfiguration().enableFieldMatching(true)
                                      .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    protected  <D, S> D populate(S source, D destination){
        return UtilMisc.populate(source, destination, modelMapper);
    }

    /***
     *
     * @param source
     * @param clazz
     * @param <D>
     * @param <S>
     * @return instance of clazz
     *
     * Ensure destination class has a public no arg constructor
     */
    public <D, S> D populate(S source, Class<D> clazz){
        return UtilMisc.populate(source,clazz,modelMapper);
    }
}
