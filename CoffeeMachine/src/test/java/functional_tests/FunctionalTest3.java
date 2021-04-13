package functional_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CoffeeMachineDto;
import models.Beverage;
import models.CoffeeMachine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.BeverageService;
import services.CoffeeMachineService;
import services.impl.BeverageServiceImpl;
import services.impl.CoffeeMachineServiceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FunctionalTest3 {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testInput() throws Exception {
        File file = new File("src/main/resources/input_3.json");
        CoffeeMachineDto dto = objectMapper.readValue(file, CoffeeMachineDto.class);

        Integer outlets = dto.getMachineDto().getOutletDto().getNumberOfOutlets();
        HashMap<String, Integer> totalIngredients = dto.getMachineDto().getIngredientsMap();
        CoffeeMachine coffeeMachine = CoffeeMachine.getInstance(outlets, totalIngredients);

        BeverageService beverageService = new BeverageServiceImpl();
        CoffeeMachineService coffeeMachineService = new CoffeeMachineServiceImpl(coffeeMachine);
        List<String> beveragesToPrepare = new ArrayList<>();

        HashMap<String, HashMap<String, Integer>> beverages = dto.getMachineDto().getBeveragesMap();
        for (String beverageName: beverages.keySet()) {
            HashMap<String, Integer> beverageIngredients = beverages.get(beverageName);
            Beverage beverage = beverageService.createBeverage(beverageName, beverageIngredients);
            coffeeMachineService.addBeverage(beverage);
            beveragesToPrepare.add(beverageName);
        }

        for (String beverage: beveragesToPrepare) {
            coffeeMachineService.prepareBeverage(beverage);
        }
        Assertions.assertEquals(6, coffeeMachine.getBeverages().size());
    }
}
