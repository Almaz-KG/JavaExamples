package designpatterns.observer.observers;

/**
 * Created by Almaz on 05.08.2015.
 */
public class ObserverImpl3 implements Observer{
    @Override
    public void handleEvent(Object event) {
        System.out.println(this + " : " + event);
    }

    @Override
    public String toString() {
        return "Observer 3";
    }
}
