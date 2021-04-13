package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Dto (Data Transfer Object) classes have been created to communicate with the outside objects such as the given input json
 * this is helpful because we can separate out our internal data models with the outside world
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeMachineDto {

    @JsonProperty("machine")
    private MachineDto machineDto;
}
