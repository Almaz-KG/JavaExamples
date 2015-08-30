package designpatterns.decorator.decorators;

import designpatterns.decorator.AbstractBeverage;

/**
 * Created by Almaz on 30.08.2015.
 */
public class ChocolateCondiment extends CondimentsDecorator {
    private AbstractBeverage beverage;
    private double price = 70;

    public ChocolateCondiment(AbstractBeverage beverage) {
        this.beverage = beverage;
        this.description = beverage.getDescription() + " + chocolate";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + price;
    }

}
