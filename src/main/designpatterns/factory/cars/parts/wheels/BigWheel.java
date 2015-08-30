package designpatterns.factory.cars.parts.wheels;

/**
 * Created by Almaz on 30.08.2015.
 */
public class BigWheel extends AbstractWheel {
    private String name = "Big 17\" wheels";

    @Override
    public String toString() {
        return name;
    }
}
