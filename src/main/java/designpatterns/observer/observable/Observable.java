package designpatterns.observer.observable;

import designpatterns.observer.observers.Observer;

/**
 * Created by Almaz on 05.08.2015.
 */
public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);

    void notifyObservers();
}
