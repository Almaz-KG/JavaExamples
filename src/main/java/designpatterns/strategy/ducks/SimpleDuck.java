package designpatterns.strategy.ducks;

/**
 * Created by Almaz on 29.08.2015.
 */
public class SimpleDuck extends AbstractDuck {
    @Override
    public void display() {
        System.out.println("Hi, I am a simple duck");
    }
}
