package designpatterns.factory.cars.car;

import designpatterns.factory.cars.CarType;
import designpatterns.factory.partfactory.AbstractCarPartsFactory;

/**
 * Created by Almaz on 30.08.2015.
 */
public class Passat extends Car {
    private AbstractCarPartsFactory factory;

    public Passat(AbstractCarPartsFactory factory) {
        this.factory = factory;
        this.type = CarType.PASSAT;
        this.body = "Sedan";
    }

    @Override
    public void init() {
        System.out.println("Configuring " + type);
        System.out.println("Body is " + body);

        this.engine = factory.createEngine();
        this.paintColor = factory.createPaint();
        this.wheels = factory.createWheel();

        System.out.println("Engine is " + engine);
        System.out.println("Paint color is " + paintColor);
        System.out.println("Wheels are " + wheels);
    }
}
