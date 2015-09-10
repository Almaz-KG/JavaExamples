package designpatterns.strategy.ducks;

import designpatterns.strategy.interfaces.fly.NoFly;

/**
 * Created by Almaz on 29.08.2015.
 */
public class RubberDuck extends AbstractDuck {
    public RubberDuck() {
        flyBehaviour = new NoFly();
    }

    @Override
    public void display() {
        System.out.println("Hi, I am a rubber duck!");
    }
}
