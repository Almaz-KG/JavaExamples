package designpatterns.decorator;

/**
 * Created by Almaz on 29.08.2015.
 */
public abstract class AbstractBeverage {
    protected String description = "";

    public abstract double getCost();

    public String getDescription() {
        return description;
    }

}
