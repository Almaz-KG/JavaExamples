package designpatterns.decorator.beverages;

import designpatterns.decorator.AbstractBeverage;

/**
 * Created by Almaz on 29.08.2015.
 */
public class BlackTea extends AbstractBeverage {
    protected double price = 200;

    public BlackTea() {
        this.description = "Black tea";
    }

    @Override
    public double getCost() {
        return price;
    }
}
