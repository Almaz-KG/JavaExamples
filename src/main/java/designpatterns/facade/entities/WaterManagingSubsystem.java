package designpatterns.facade.entities;

/**
 * Created by Almaz on 14.09.2015.
 */
public class WaterManagingSubsystem {

    public void fillWater(int litres){
        System.out.println("Fill with " + litres + " litres of water");
    }

    public void emptyWater(){
        System.out.println("Empty water tank");
    }

}
