package designpatterns.command.systems;

/**
 * Created by Almaz on 07.09.2015.
 */
public class Music extends AbstractSystem {
    @Override
    public void turnOn() {
        System.out.println("Музыка включена");
        state = State.ON;
    }

    @Override
    public void turnOff() {
        System.out.println("Музыка выключена");
        state = State.OFF;
    }
}
