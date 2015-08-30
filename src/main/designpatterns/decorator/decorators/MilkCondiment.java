package designpatterns.decorator.decorators;

import designpatterns.decorator.AbstractBeverage;

/**
 * Created by Almaz on 30.08.2015.
 */
public class MilkCondiment extends CondimentsDecorator {
    private AbstractBeverage beverage;
    private double price = 50;

    public MilkCondiment(AbstractBeverage beverage) {
        this.beverage = beverage;
        this.description = beverage.getDescription() + " + milk";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + price;
    }
}
