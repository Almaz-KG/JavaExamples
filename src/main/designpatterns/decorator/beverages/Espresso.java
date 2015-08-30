package designpatterns.decorator.beverages;

import designpatterns.decorator.AbstractBeverage;

/**
 * Created by Almaz on 29.08.2015.
 */
public class Espresso extends AbstractBeverage {
    protected double price = 150;

    public Espresso() {
        this.description = "Small portion of strong coffee";
    }

    @Override
    public double getCost() {
        return price;
    }
}
