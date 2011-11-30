package org.nthdimenzion.object.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class UtilMisc {

    private static final Logger logger = LoggerFactory.getLogger(UtilMisc.class);

    public static void threadSleep(long milliSeconds) {
    try {
        Thread.sleep(milliSeconds);
    } catch (InterruptedException e) {
        logger.error("Error in threadSleep",e);
    }
    }

}
