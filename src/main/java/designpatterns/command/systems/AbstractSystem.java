package designpatterns.command.systems;

/**
 * Created by Almaz on 07.09.2015.
 */
public abstract class AbstractSystem {
    protected State state;

    public abstract void turnOn();
    public abstract void turnOff();

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }

    public static enum State {
        ON,
        OFF
    }
}
