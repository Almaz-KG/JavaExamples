package designpatterns.factory.partfactory;

import designpatterns.factory.cars.parts.engines.AbstractEngine;
import designpatterns.factory.cars.parts.engines.DieselEngine;
import designpatterns.factory.cars.parts.paints.AbstractPaint;
import designpatterns.factory.cars.parts.paints.WhitePaint;
import designpatterns.factory.cars.parts.wheels.AbstractWheel;
import designpatterns.factory.cars.parts.wheels.BigWheel;

/**
 * Created by Almaz on 30.08.2015.
 */
public class DeutschCarPartsFactory extends AbstractCarPartsFactory {
    @Override
    public AbstractEngine createEngine() {
        return new DieselEngine();
    }

    @Override
    public AbstractPaint createPaint() {
        return new WhitePaint();
    }

    @Override
    public AbstractWheel createWheel() {
        return new BigWheel();
    }
}
