package designpatterns.facade.entities;

/**
 * Created by Almaz on 14.09.2015.
 */
public class WashingMachine {
    private Dryer dryer;
    private Engine engine;
    private Thermo thermo;
    private WaterManagingSubsystem waterManagingSubsystem;

    public WashingMachine(Dryer dryer, Engine engine, Thermo thermo, WaterManagingSubsystem waterManagingSubsystem) {
        this.dryer = dryer;
        this.engine = engine;
        this.thermo = thermo;
        this.waterManagingSubsystem = waterManagingSubsystem;
    }

    public void washCotton(){
        waterManagingSubsystem.fillWater(10);
        thermo.warmUp(70);
        engine.rotate();
        engine.rotate();
        engine.rotate();
        engine.stop();
        waterManagingSubsystem.emptyWater();
        dryer.dry(30, 1000);
        waterManagingSubsystem.fillWater(15);
        thermo.warmUp(50);
        engine.rotate();
        engine.stop();
        waterManagingSubsystem.emptyWater();
        dryer.dry(60, 1500);
    }

    public void washWool(){
        // Wool
        waterManagingSubsystem.fillWater(7);
        thermo.warmUp(30);
        engine.rotate();
        engine.stop();
        waterManagingSubsystem.emptyWater();
        dryer.dry(30, 500);
        waterManagingSubsystem.fillWater(10);
        thermo.warmUp(20);
        engine.rotate();
        engine.stop();
        waterManagingSubsystem.emptyWater();
        dryer.dry(60, 700);
    }
}
