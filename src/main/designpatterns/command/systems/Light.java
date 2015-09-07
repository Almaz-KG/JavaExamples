package designpatterns.command.systems;

import java.lang.*;

/**
 * Created by Almaz on 07.09.2015.
 */
public class Light extends AbstractSystem {
    protected LightState lightState = LightState.OFF;

    @Override
    public void turnOn() {
        switch (lightState){
            case OFF:
                lightState = lightState.LOW;
                break;
            case LOW:
                lightState = lightState.MEDIUM;
                break;
            case MEDIUM:
                lightState = lightState.HIGH;
                break;
            case HIGH:
                lightState = lightState.OFF;
                break;
        }
        print();
    }

    @Override
    public void turnOff() {
        lightState = lightState.OFF;
        print();
    }

    public enum LightState{
        OFF,
        LOW,
        MEDIUM,
        HIGH
    }

    public LightState getLightState() {
        return lightState;
    }
    public void setLightState(LightState lightState) {
        this.lightState = lightState;
        print();
    }

    private void print(){
        switch (lightState){
            case OFF:
                System.out.println("Свет выключен");
                break;
            case LOW:
                System.out.println("Свет тусклый");
                break;
            case MEDIUM:
                System.out.println("Свет средний");
                break;
            case HIGH:
                System.out.println("Свет яркий");
                break;
        }
    }
}
