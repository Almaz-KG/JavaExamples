package designpatterns.command.commands;

import designpatterns.command.systems.TV;

/**
 * Created by Almaz on 07.09.2015.
 */
public class TVCommand implements Command {
    private TV tv;

    public TVCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOn();
    }
    @Override
    public void undo() {

        tv.turnOff();
    }
    @Override

    public String toString() {
        return "Включить телевизор";
    }
}
