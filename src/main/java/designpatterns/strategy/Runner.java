package designpatterns.strategy;

import designpatterns.strategy.ducks.*;
import designpatterns.strategy.interfaces.fly.FlyWithWings;
import designpatterns.strategy.interfaces.quack.SimpleQuack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Almaz on 29.08.2015.
 */
public class Runner {
    public static void main(String[] args) {
        List<AbstractDuck> ducks = new ArrayList<>();

        ducks.add(new ExoticDuck());
        ducks.add(new SimpleDuck());
        ducks.add(new WoodenDuck());
        ducks.add(new RubberDuck());

        for (AbstractDuck duck : ducks) {
            duck.display();
            duck.swim();
            duck.quack();
            duck.fly();
            System.out.println();
        }

        AbstractDuck upgradableDuck = new UpgradableDuck();
        upgradableDuck.display();
        upgradableDuck.swim();
        upgradableDuck.fly();
        upgradableDuck.quack();

        upgradableDuck.setFlyBehaviour(new FlyWithWings());
        upgradableDuck.setQuackBehaviour(new SimpleQuack());

        upgradableDuck.fly();
        upgradableDuck.quack();
    }
}
