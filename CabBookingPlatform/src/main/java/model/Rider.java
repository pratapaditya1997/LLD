package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Rider extends Person{
    private int riderId;
    private Location location;

    public Rider(String name, int age, Location location) {
        super(name, age);
        this.location = location;
    }
}
