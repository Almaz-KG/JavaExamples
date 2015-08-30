package designpatterns.decorator.decorators;

import designpatterns.decorator.AbstractBeverage;

/**
 * Created by Almaz on 30.08.2015.
 */
public class SugarCondiment extends CondimentsDecorator {
    private AbstractBeverage beverage;
    private double price = 10;

    public SugarCondiment(AbstractBeverage beverage) {
        this.beverage = beverage;
        this.description = beverage.getDescription() + " + sugar";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + price;
    }
}
