package designpatterns.strategy.interfaces.quack;

/**
 * Created by Almaz on 29.08.2015.
 */
public class NoQuack implements Quackable {
    @Override
    public void quack() {
        System.out.println("-----");
    }
}
