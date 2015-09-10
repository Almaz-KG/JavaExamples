package designpatterns.strategy.interfaces.fly;

/**
 * Created by Almaz on 29.08.2015.
 */
public class FlyWithWings implements Flyable {
    @Override
    public void fly() {
        System.out.println("I am fly with my wings");
    }
}
