package designpatterns.factory;

import designpatterns.factory.cars.CarType;
import designpatterns.factory.facilities.RussianVolkswagenFacility;
import designpatterns.factory.facilities.VolkswagenFacility;

/**
 * Created by Almaz on 30.08.2015.
 */
public class Runner {
    public static void main(String[] args) {
        VolkswagenFacility facility =
                new RussianVolkswagenFacility();
        facility.getCar(CarType.GOLF);
        facility.getCar(CarType.PASSAT);
        facility.getCar(CarType.TIGUAN);
        facility.getCar(CarType.TOUAREG);
    }
}
