package models;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Thread safe singleton implementation of Coffee Machine class. This is done so that clients can not create multiple
 * instances of coffee machine
 *
 * Algorithm;
 * Multi threaded system with N threads is invoked to represent N outlet coffee machine
 * It queues up all the system requests from the input and tries to create the beverages
 */
@Getter
public class CoffeeMachine {
    private static final Logger logger = LogManager.getLogger(CoffeeMachine.class);

    private Integer outlets;
    private Map<String, Integer> totalIngredients;
    private List<Beverage> beverages;

    private static CoffeeMachine instance;

    private CoffeeMachine(Integer outlets, Map<String, Integer> totalIngredients) {
        this.outlets = outlets;
        this.totalIngredients = totalIngredients;
        this.beverages = new ArrayList<>();
    }

    public static synchronized CoffeeMachine getInstance(Integer outlets, Map<String, Integer> totalIngredients) {
        if (instance == null) {
            instance = new CoffeeMachine(outlets, totalIngredients);
        }
        return instance;
    }

    /**
     * Method to check if the requested beverage is available in the Coffee Machine
     * @param beverageName
     * @return
     */
    public boolean isValidBeverage(String beverageName) {
        List<Beverage> availableBeverage = beverages
                .stream()
                .filter(bev -> bev.getName().equalsIgnoreCase(beverageName))
                .collect(Collectors.toList());
        if (availableBeverage.isEmpty()) return false;
        return true;
    }

    /**
     * thread-safe synchronized method to update the ingredients inventory of the Coffee Machine when a beverage is being
     * prepared
     * @param beverageName
     * @return
     */
    public synchronized boolean checkAndUpdateIngredients(String beverageName) {
        List<Beverage> beverageList = beverages.stream()
                .filter(bev -> bev.getName().equalsIgnoreCase(beverageName))
                .collect(Collectors.toList());
        if (beverageList.isEmpty()) return false;
        Beverage currentBeverage = beverageList.get(0);

        Map<String, Integer> requiredIngredients = currentBeverage.getIngredients();
        boolean isPossible = true;

        for (String ingredient: requiredIngredients.keySet()) {
            Integer totalCount = totalIngredients.getOrDefault(ingredient, -1);
            if (totalCount <= 0) {
                logger.error("{} cannot be prepared because {} is not available", beverageName, ingredient);
                isPossible = false;
                break;
            } else if (requiredIngredients.get(ingredient) > totalCount) {
                logger.error("{} cannot be prepared because {} is not sufficient", beverageName, ingredient);
                isPossible = false;
                break;
            }
        }

        if (isPossible) {
            for (String ingredient: requiredIngredients.keySet()) {
                int existingCount = totalIngredients.get(ingredient);
                totalIngredients.put(ingredient, existingCount - requiredIngredients.get(ingredient));
            }
        }
        return isPossible;
    }
}
