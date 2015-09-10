package designpatterns.factory.facilities;

import designpatterns.factory.cars.CarType;
import designpatterns.factory.cars.car.Car;

/**
 * Created by Almaz on 30.08.2015.
 */
public abstract class VolkswagenFacility {
    public Car getCar(CarType type){
        Car car = createCar(type);
        car.init();
        System.out.println();

        return car;
    }

    public abstract Car createCar(CarType type);
}
