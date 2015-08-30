package designpatterns.factory.partfactory;

import designpatterns.factory.cars.parts.engines.AbstractEngine;
import designpatterns.factory.cars.parts.paints.AbstractPaint;
import designpatterns.factory.cars.parts.wheels.AbstractWheel;

/**
 * Created by Almaz on 30.08.2015.
 */
public abstract class AbstractCarPartsFactory {
    public abstract AbstractEngine createEngine();
    public abstract AbstractPaint createPaint();
    public abstract AbstractWheel createWheel();
}
