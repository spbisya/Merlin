package com.foxfinity.merlin.utils;

import android.content.Context;

/**
 * Project Merlin. Created by Izya Pitersky on 3/19/17.
 */

public class HelperPreferences {

    public static boolean isWhiteTheme(Context context) {
        return context.getSharedPreferences("merlin", 0).getBoolean("isWhite", true);
    }

    public static void changeTheme(Context context, boolean isWhite){
        context.getSharedPreferences("merlin", 0).edit().putBoolean("isWhite", isWhite).apply();
    }
}
