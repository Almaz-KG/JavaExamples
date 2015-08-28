package designpatterns.strategy.ducks;

import designpatterns.strategy.interfaces.fly.NoFly;
import designpatterns.strategy.interfaces.quack.NoQuack;

/**
 * Created by Almaz on 29.08.2015.
 */
public class WoodenDuck extends AbstractDuck {
    public WoodenDuck() {
        flyBehaviour = new NoFly();
        quackBehaviour = new NoQuack();
    }

    @Override
    public void display() {
        System.out.println("Hi, I am a wooden duck");
    }
}
