package utils;

import models.CoffeeMachine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class IndicatorUtil {
    private static final Logger logger = LogManager.getLogger(IndicatorUtil.class);

    private IndicatorUtil(){}

    /**
     * prints the current status of the ingredients
     * @param coffeeMachine
     */
    public static void printCurrentIngredientsStatus(CoffeeMachine coffeeMachine) {
        Map<String, Integer> currentStatus = coffeeMachine.getTotalIngredients();
        for (String ingredient: currentStatus.keySet()) {
            logger.info("Ingredient: {} has {} quantity left", ingredient, currentStatus.get(ingredient));
        }
    }
}
