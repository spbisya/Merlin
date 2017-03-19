package com.foxfinity.merlin.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Project Merlin. Created by Izya Pitersky on 3/19/17.
 */

public class TypefaceHelper {
    private static Typeface regular;
    private static Typeface italic;

    public static void init(Context context) {
        regular = Typeface.createFromAsset(context.getAssets(), "fonts/timesRegular.ttf");
        italic = Typeface.createFromAsset(context.getAssets(), "fonts/timesItalic.ttf");
    }

    public static Typeface getTypeface(int type) {
        return type == 0 ? regular : italic;
    }

}
