package com.gof.behavioural.observer;



// Implemented by Cricket data to communicate
// with observers
public interface Subject
{
    public void registerObserver(Observer o);
    public void unregisterObserver(Observer o);
    public void notifyObservers();
}
