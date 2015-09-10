package designpatterns.strategy.ducks;

import designpatterns.strategy.interfaces.quack.ExoticQuack;

/**
 * Created by Almaz on 29.08.2015.
 */
public class ExoticDuck extends AbstractDuck {
    public ExoticDuck() {
        quackBehaviour = new ExoticQuack();
    }

    @Override
    public void display() {
        System.out.println("Hi, I am an exotic duck!");
    }
}
