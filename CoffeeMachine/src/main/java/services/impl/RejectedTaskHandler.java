package services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * handler to be called when {@link ThreadPoolExecutor} reaches the max capacity and can not take further requests
 */
public class RejectedTaskHandler implements RejectedExecutionHandler {
    private static final Logger logger = LogManager.getLogger(RejectedTaskHandler.class);

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        logger.info("RejectedTaskHandler: the beverage request {} has been rejected by the coffee machine", r.toString());
    }
}
