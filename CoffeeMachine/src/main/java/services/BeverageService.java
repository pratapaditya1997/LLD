package services;

import models.Beverage;

import java.util.Map;

public interface BeverageService {
    /**
     * creates a beverage
     * @param name
     * @param ingredients
     * @return
     */
    Beverage createBeverage(String name, Map<String, Integer> ingredients);
}
