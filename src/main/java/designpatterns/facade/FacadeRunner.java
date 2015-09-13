package designpatterns.facade;

import designpatterns.facade.entities.*;

/**
 * Created by Almaz on 14.09.2015.
 */
public class FacadeRunner {
    public static void main(String[] args) {
        WaterManagingSubsystem water = new WaterManagingSubsystem();
        Thermo thermo = new Thermo();
        Engine engine = new Engine();
        Dryer dryer = new Dryer();

        WashingMachine washingMachine = new WashingMachine(dryer, engine, thermo, water);

        washingMachine.washCotton();

        System.out.println("--------");
        washingMachine.washWool();
    }
}
