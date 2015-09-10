package designpatterns.factory.cars.parts.engines;

/**
 * Created by Almaz on 30.08.2015.
 */
public class DieselEngine extends AbstractEngine {
    private String name = "Diesel engine";

    @Override
    public String toString() {
        return name;
    }
}
