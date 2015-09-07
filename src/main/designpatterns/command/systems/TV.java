package designpatterns.command.systems;

/**
 * Created by Almaz on 07.09.2015.
 */
public class TV extends AbstractSystem {
    @Override
    public void turnOn() {
        System.out.println("Телевизор включен");
        state = State.ON;
    }

    @Override
    public void turnOff() {
        System.out.println("Телевизор выключен");
        state = State.OFF;
    }
}
