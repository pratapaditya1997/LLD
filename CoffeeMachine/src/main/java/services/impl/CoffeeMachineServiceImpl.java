package services.impl;

import lombok.NonNull;
import models.Beverage;
import models.CoffeeMachine;
import services.CoffeeMachineService;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CoffeeMachineServiceImpl implements CoffeeMachineService {
    private CoffeeMachine coffeeMachine;
    private final int MAX_QUEUED_REQUESTS = 100;
    private ThreadPoolExecutor executor;

    public CoffeeMachineServiceImpl(@NonNull final CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
        Integer numThreads = coffeeMachine.getOutlets();
        executor = new ThreadPoolExecutor(numThreads, numThreads, 5000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(MAX_QUEUED_REQUESTS));
        executor.setRejectedExecutionHandler(new RejectedTaskHandler());
    }

    /**
     * {@inheritDoc}
     * @param beverage
     */
    @Override
    public void addBeverage(@NonNull final Beverage beverage) {
        coffeeMachine.getBeverages().add(beverage);
    }

    /**
     * {@inheritDoc}
     * @param beverageName
     */
    @Override
    public void prepareBeverage(@NonNull final String beverageName) {
        PrepareBeverageTask task = new PrepareBeverageTask(beverageName, coffeeMachine);
        executor.execute(task);
    }

    /**
     * {@inheritDoc}
     * @param ingredient
     * @param quantity
     */
    @Override
    public void addIngredient(@NonNull final String ingredient, Integer quantity) {
        Integer prevQuantity = coffeeMachine.getTotalIngredients().getOrDefault(ingredient, -1);
        Integer newQuantity = quantity;
        if (prevQuantity != -1) {
            newQuantity += prevQuantity;
        }
        coffeeMachine.getTotalIngredients().put(ingredient, newQuantity);
    }
}
