package designpatterns.command.commands;

import designpatterns.command.systems.Teapot;

/**
 * Created by Almaz on 07.09.2015.
 */
public class TeapotCommand implements Command {
    private Teapot teapot;

    public TeapotCommand(Teapot teapot) {
        this.teapot = teapot;
    }

    @Override
    public void execute() {
        teapot.turnOn();
    }
    @Override
    public void undo() {
        teapot.turnOff();
    }

    @Override
    public String toString() {
        return "Включить чайник";
    }
}
