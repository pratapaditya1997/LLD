import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CoffeeMachineDto;
import models.Beverage;
import models.CoffeeMachine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.BeverageService;
import services.CoffeeMachineService;
import services.impl.BeverageServiceImpl;
import services.impl.CoffeeMachineServiceImpl;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CoffeeMachineApplication {
    private static final Logger logger = LogManager.getLogger(CoffeeMachineApplication.class);

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/resources/input_1.json");
        CoffeeMachineDto dto = objectMapper.readValue(file, CoffeeMachineDto.class);

        Integer outlets = dto.getMachineDto().getOutletDto().getNumberOfOutlets();
        HashMap<String, Integer> totalIngredients = dto.getMachineDto().getIngredientsMap();
        CoffeeMachine coffeeMachine = CoffeeMachine.getInstance(outlets, totalIngredients);

        BeverageService beverageService = new BeverageServiceImpl();
        CoffeeMachineService coffeeMachineService = new CoffeeMachineServiceImpl(coffeeMachine);

        HashMap<String, HashMap<String, Integer>> beverages = dto.getMachineDto().getBeveragesMap();
        for (String beverageName: beverages.keySet()) {
            HashMap<String, Integer> beverageIngredients = beverages.get(beverageName);
            Beverage beverage = beverageService.createBeverage(beverageName, beverageIngredients);
            coffeeMachineService.addBeverage(beverage);
        }

        List<String> beveragesToPrepare = Arrays.asList("hot_tea", "hot_coffee", "black_tea", "green_tea");
        for (String beverage: beveragesToPrepare) {
            coffeeMachineService.prepareBeverage(beverage);
        }
    }
}
