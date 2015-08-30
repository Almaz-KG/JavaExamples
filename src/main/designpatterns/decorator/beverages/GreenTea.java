package designpatterns.decorator.beverages;

import designpatterns.decorator.AbstractBeverage;

/**
 * Created by Almaz on 30.08.2015.
 */
public class GreenTea extends AbstractBeverage {
    protected double price = 125;

    public GreenTea() {
        this.description = "Green leaf tea";
    }

    @Override
    public double getCost() {
        return price;
    }
}
