package designpatterns.strategy.interfaces.fly;

/**
 * Created by Almaz on 29.08.2015.
 */
public class NoFly implements Flyable {
    @Override
    public void fly() {
        System.out.println("_____");
    }
}
