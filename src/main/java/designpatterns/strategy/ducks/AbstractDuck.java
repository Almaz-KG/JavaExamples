package designpatterns.strategy.ducks;


import designpatterns.strategy.interfaces.fly.FlyWithWings;
import designpatterns.strategy.interfaces.fly.Flyable;
import designpatterns.strategy.interfaces.quack.Quackable;
import designpatterns.strategy.interfaces.quack.SimpleQuack;

/**
 * Created by Almaz on 29.08.2015.
 */
public abstract class AbstractDuck {
    protected Flyable flyBehaviour;
    protected Quackable quackBehaviour;


    protected AbstractDuck() {
        flyBehaviour = new FlyWithWings();
        quackBehaviour = new SimpleQuack();
    }

    public abstract void display();

    public void swim() {
        System.out.println("I am swimming");
    }

    public void fly(){
        flyBehaviour.fly();
    }

    public void quack(){
        quackBehaviour.quack();
    }

    public void setFlyBehaviour(Flyable flyBehaviour) {
        this.flyBehaviour = flyBehaviour;
    }

    public void setQuackBehaviour(Quackable quackBehaviour) {
        this.quackBehaviour = quackBehaviour;
    }
}
