package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MachineDto {

    @JsonProperty("outlets")
    private OutletDto outletDto;

    @JsonProperty("total_items_quantity")
    private HashMap<String, Integer> ingredientsMap;

    @JsonProperty("beverages")
    private HashMap<String, HashMap<String, Integer>> beveragesMap;
}
