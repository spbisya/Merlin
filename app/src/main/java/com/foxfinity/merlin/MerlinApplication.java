package com.foxfinity.merlin;

import android.app.Application;

import com.foxfinity.merlin.network.Api;

/**
 * Project Merlin. Created by Izya Pitersky on 3/18/17.
 */

public class MerlinApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Api.init();
    }
}
