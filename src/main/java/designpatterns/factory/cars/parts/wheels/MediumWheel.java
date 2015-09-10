package designpatterns.factory.cars.parts.wheels;

/**
 * Created by Almaz on 30.08.2015.
 */
public class MediumWheel extends AbstractWheel {
    private String name = "Medium 16\" wheels";

    @Override
    public String toString() {
        return name;
    }
}
