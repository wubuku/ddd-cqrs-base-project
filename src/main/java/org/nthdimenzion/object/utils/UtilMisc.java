package org.nthdimenzion.object.utils;

import com.google.common.collect.Lists;
import org.nthdimenzion.ddd.domain.IdGeneratingArcheType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

public final class UtilMisc {

    private static final Logger logger = LoggerFactory.getLogger(UtilMisc.class);

    public static void threadSleep(long milliSeconds) {
    try {
        Thread.sleep(milliSeconds);
    } catch (InterruptedException e) {
        logger.error("Error in threadSleep",e);
    }
    }

    public static List<Long> extractId(Collection<? extends IdGeneratingArcheType> entities){
        if(UtilValidator.isEmpty(entities)){
            return Lists.newArrayList();
        }
        List<Long> entityIds = Lists.newArrayList();
        for(IdGeneratingArcheType entity : entities){
            entityIds.add(entity.getId());
        }
        return entityIds;
    }

}
