package designpatterns.observer.observable;

import designpatterns.observer.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Almaz on 05.08.2015.
 */
public class ObservableImpl implements Observable {
    protected List<Observer> observerList;

    public ObservableImpl() {
        this.observerList = new ArrayList<>();
    }
    public ObservableImpl(List<Observer> observerList) {
        this.observerList = observerList;
    }

    @Override
    public void addObserver(Observer observer) {
        if(observer != null)
            this.observerList.add(observer);
    }
    @Override
    public void removeObserver(Observer observer) {
        if(observer != null)
            this.observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observerList.forEach((o) -> o.handleEven());
    }
}
