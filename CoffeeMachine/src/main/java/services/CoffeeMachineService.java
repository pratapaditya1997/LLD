package services;

import models.Beverage;
import models.CoffeeMachine;

public interface CoffeeMachineService {
    /**
     * adds given {@link Beverage} to the {@link CoffeeMachine}
     * @param beverage
     */
    void addBeverage(Beverage beverage);

    /**
     * method to prepare a {@link Beverage} given the beverage name
     * @param beverageName
     */
    void prepareBeverage(String beverageName);

    /**
     * adds ingredient to the {@link CoffeeMachine
     * this method is used in cases when you want to refill the machine
     * @param ingredient
     * @param quantity
     */
    void addIngredient(String ingredient, Integer quantity);
}
