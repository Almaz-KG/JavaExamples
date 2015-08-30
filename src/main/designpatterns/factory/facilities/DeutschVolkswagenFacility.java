package designpatterns.factory.facilities;

import designpatterns.factory.cars.CarType;
import designpatterns.factory.cars.car.*;
import designpatterns.factory.partfactory.AbstractCarPartsFactory;
import designpatterns.factory.partfactory.DeutschCarPartsFactory;

/**
 * Created by Almaz on 30.08.2015.
 */
public class DeutschVolkswagenFacility extends VolkswagenFacility {
    protected AbstractCarPartsFactory factory;

    public DeutschVolkswagenFacility() {
        this.factory = new DeutschCarPartsFactory();
    }

    @Override
    public Car createCar(CarType type) {
        switch (type) {
            case GOLF:
                return new Golf(factory);
            case PASSAT:
                return new Passat(factory);
            case TIGUAN:
                return new Tiguan(factory);
            case TOUAREG:
                return new Touareg(factory);
            default:
                throw new IllegalArgumentException("Unsupported car type");
        }
    }
}
