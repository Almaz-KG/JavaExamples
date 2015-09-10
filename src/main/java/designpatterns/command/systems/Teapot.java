package designpatterns.command.systems;

/**
 * Created by Almaz on 07.09.2015.
 */
public class Teapot extends AbstractSystem {
    @Override
    public void turnOn() {
        System.out.println("Чайник включен");
        state = State.ON;
    }

    @Override
    public void turnOff() {
        System.out.println("Чайник выключен");
        state = State.OFF;
    }
}
