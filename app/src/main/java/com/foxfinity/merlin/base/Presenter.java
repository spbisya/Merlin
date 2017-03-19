package com.foxfinity.merlin.base;

/**
 * Project Merlin. Created by Izya Pitersky on 3/19/17.
 */

public abstract class Presenter<V extends IView> implements IPresenter<V> {
    private V view;
    private final Object lock = new Object();

    @Override
    public void attachView(V view) {
        synchronized (lock) {
            this.view = view;
        }
        onViewAttached();
    }

    @Override
    public void detachView() {
        synchronized (lock) {
            this.view = null;
        }
        onViewDetached();
    }

    protected final V getView() {
        synchronized (lock) {
            return this.view;
        }
    }

    protected abstract void onViewAttached();

    protected abstract void onViewDetached();
}
