package designpatterns.facade.entities;

/**
 * Created by Almaz on 14.09.2015.
 */
public class Dryer {

    public void dry(int seconds, int intensity){
        System.out.println("Drying " + seconds + "seconds with intensity " + intensity);
    }
}
