package designpatterns.factory.cars.car;

import designpatterns.factory.cars.CarType;
import designpatterns.factory.cars.parts.engines.AbstractEngine;
import designpatterns.factory.cars.parts.paints.AbstractPaint;
import designpatterns.factory.cars.parts.wheels.AbstractWheel;

/**
 * Created by Almaz on 30.08.2015.
 */
public abstract class Car {
    protected CarType type;
    protected String body = "caravan";

    protected AbstractEngine engine;
    protected AbstractPaint paintColor;
    protected AbstractWheel wheels;


    public abstract void init();


    public void assembleBody(){
        System.out.println("Body is assembled");
    }

    public void installEngine(){
        System.out.println("Engine is in its place");
    }

    public void paint(){
        System.out.println("Car is painted");
    }

    public void installWheels(){
        System.out.println("Wheels are installed");
    }

}
