package services.impl;

import lombok.AllArgsConstructor;
import models.Beverage;
import services.BeverageService;

import java.util.Map;

@AllArgsConstructor
public class BeverageServiceImpl implements BeverageService {

    /**
     * {@inheritDoc}
     * @param name
     * @param ingredients
     * @return
     */
    @Override
    public Beverage createBeverage(String name, Map<String, Integer> ingredients) {
        Beverage beverage = new Beverage(name, ingredients);
        return beverage;
    }
}
