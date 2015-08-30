package designpatterns.factory.partfactory;

import designpatterns.factory.cars.parts.engines.AbstractEngine;
import designpatterns.factory.cars.parts.engines.GasolineEngine;
import designpatterns.factory.cars.parts.paints.AbstractPaint;
import designpatterns.factory.cars.parts.paints.BlackPaint;
import designpatterns.factory.cars.parts.wheels.AbstractWheel;
import designpatterns.factory.cars.parts.wheels.MediumWheel;

/**
 * Created by Almaz on 30.08.2015.
 */
public class RussianCarPartsFactory extends AbstractCarPartsFactory {
    @Override
    public AbstractEngine createEngine() {
        return new GasolineEngine();
    }

    @Override
    public AbstractPaint createPaint() {
        return new BlackPaint();
    }

    @Override
    public AbstractWheel createWheel() {
        return new MediumWheel();
    }
}
