package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Driver extends Person{
    private int driverId;

    public Driver(String name, int age) {
        super(name, age);
    }
}
