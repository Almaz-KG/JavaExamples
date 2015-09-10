package designpatterns.observer.observable;



import designpatterns.observer.observers.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Almaz on 05.08.2015.
 */
public class ObservableImpl implements Observable {
    private static final String DEFAULT_EVENT_MESSAGE = "Action performed";

    protected List<Observer> observerList;
    protected String currentEventMessage;

    public ObservableImpl() {
        this.observerList = new ArrayList<>();
    }
    public ObservableImpl(List<Observer> observerList) {
        this.observerList = observerList;
    }

    @Override
    public void addObserver(Observer observer) {
        if(observer != null) {
            this.observerList.add(observer);
            currentEventMessage = observer + " added!";
            this.notifyObservers();
            currentEventMessage = DEFAULT_EVENT_MESSAGE;
        }
    }
    @Override
    public void removeObserver(Observer observer) {
        if(observer != null) {
            this.observerList.remove(observer);
            currentEventMessage = observer + " removed!";
            this.notifyObservers();
            currentEventMessage = DEFAULT_EVENT_MESSAGE;
        }
    }

    @Override
    public void notifyObservers() {
        observerList.forEach((o) -> o.handleEvent(currentEventMessage));
    }
}
