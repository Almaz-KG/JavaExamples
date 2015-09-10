package designpatterns.factory.cars.parts.engines;

/**
 * Created by Almaz on 30.08.2015.
 */
public class GasolineEngine extends AbstractEngine {
    private String name = "Gasoline engine";

    @Override
    public String toString() {
        return name;
    }
}
