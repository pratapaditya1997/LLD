package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.Map;

@Getter
public class Beverage {
    private String name;
    private Map<String, Integer> ingredients;

    public Beverage(@NonNull final String name, @NonNull final Map<String, Integer> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }
}
