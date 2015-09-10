package designpatterns.command.commands;

import designpatterns.command.systems.Light;

import java.util.Stack;

/**
 * Created by Almaz on 07.09.2015.
 */
public class LightsCommand implements Command{
    private Light light;
    private Stack<Light.LightState> states;

    public LightsCommand(Light light) {
        this.light = light;
        this.states = new Stack<>();
    }

    @Override
    public void execute() {
        states.push(light.getLightState());
        light.turnOn();
    }

    @Override
    public void undo() {
        Light.LightState pop = states.pop();
        light.setLightState(pop);
    }

    @Override
    public String toString() {
        return "Включить свет";
    }
}
