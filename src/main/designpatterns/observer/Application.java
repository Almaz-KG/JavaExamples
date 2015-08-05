package designpatterns.observer;

import designpatterns.observer.observable.Observable;
import designpatterns.observer.observable.ObservableImpl;
import designpatterns.observer.observers.Observer;
import designpatterns.observer.observers.ObserverImpl1;
import designpatterns.observer.observers.ObserverImpl2;
import designpatterns.observer.observers.ObserverImpl3;

/**
 * Created by Almaz on 05.08.2015.
 */
public class Application {
    public static void main(String[] args) {
        Observable observable = new ObservableImpl();

        Observer obs1 = new ObserverImpl1();
        Observer obs2 = new ObserverImpl2();
        Observer obs3 = new ObserverImpl3();

        observable.addObserver(obs1);
        observable.addObserver(obs2);
        observable.removeObserver(obs1);
        observable.addObserver(obs3);
        observable.notifyObservers();
        observable.addObserver(obs1);

        observable.removeObserver(obs2);
        observable.removeObserver(obs3);
        observable.removeObserver(obs1);


    }
}
