package designpatterns.strategy.ducks;

import designpatterns.strategy.interfaces.fly.NoFly;
import designpatterns.strategy.interfaces.quack.NoQuack;

/**
 * Created by Almaz on 29.08.2015.
 */
public class UpgradableDuck extends AbstractDuck {

    public UpgradableDuck() {
        flyBehaviour = new NoFly();
        quackBehaviour = new NoQuack();
    }

    @Override
    public void display() {
        System.out.println("Hi, I am an upgradable duck!");
    }
}
