package org.nthdimenzion.object.utils;

import com.google.common.collect.Lists;
import org.apache.commons.beanutils.BeanUtils;
import org.modelmapper.ModelMapper;
import org.nthdimenzion.ddd.domain.IdGeneratingArcheType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class UtilMisc {

    private static final Logger logger = LoggerFactory.getLogger(UtilMisc.class);

    public static void threadSleep(long milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            logger.error("Error in threadSleep", e);
        }
    }

    public static List<Long> extractId(Collection<? extends IdGeneratingArcheType> entities) {
        if (UtilValidator.isEmpty(entities)) {
            return Lists.newArrayList();
        }
        List<Long> entityIds = Lists.newArrayList();
        for (IdGeneratingArcheType entity : entities) {
            entityIds.add(entity.getId());
        }
        return entityIds;
    }

    public static <D, S> D populate(S source, D destination, ModelMapper modelMapper) {
        if (source instanceof Map) {
            try {
                BeanUtils.populate(destination, (Map) source);
            } catch (IllegalAccessException e) {
                destination = null;
                logger.error("Could not copy properties of map into bean ", e);
            } catch (InvocationTargetException e) {
                destination = null;
                logger.error("Could not copy properties of map into bean ", e);
            }
        }
        modelMapper.map(source, destination);
        return destination;
    }

        /**
     * @param source
     * @param clazz
     * @return instance of clazz
     *         <p/>
     *         Ensure destination class has a public no arg constructor
     */
     public static <D, S> D populate(S source, Class<D> clazz, ModelMapper modelMapper){
         D destination = org.springframework.beans.BeanUtils.instantiate(clazz);
         return populate(source,destination,modelMapper);
     }

     public static <T> T returnDefaultIfNull(T input,T defaultValue){
          return  input==null ? defaultValue : input;
     }
}
