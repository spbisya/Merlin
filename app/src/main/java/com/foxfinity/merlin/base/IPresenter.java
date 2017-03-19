package com.foxfinity.merlin.base;

public interface IPresenter<V> {

    void attachView(V view);

    void detachView();
}
