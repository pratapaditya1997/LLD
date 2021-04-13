package services.impl;

import lombok.AllArgsConstructor;
import models.Beverage;
import models.CoffeeMachine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Task/Job created to prepare beverages
 * This is using {@link java.util.concurrent.ThreadPoolExecutor} to maintain concurrency
 */
@AllArgsConstructor
public class PrepareBeverageTask implements  Runnable {
    private static final Logger logger = LogManager.getLogger(PrepareBeverageTask.class);

    private String beverage;
    private CoffeeMachine coffeeMachine;

    @Override
    public void run() {
        if (coffeeMachine.isValidBeverage(beverage)) {
            if (coffeeMachine.checkAndUpdateIngredients(beverage)) {
                logger.info("{} is prepared", beverage);
            }
        }
    }
}
