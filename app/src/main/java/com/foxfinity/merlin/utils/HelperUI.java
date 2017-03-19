package com.foxfinity.merlin.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.EdgeEffect;

import java.lang.reflect.Field;

/**
 * Project Merlin. Created by Izya Pitersky on 3/19/17.
 */

public class HelperUI {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setEdgeEffectL(View scrollableView, int color) {
        final String[] edgeGlows = {"mEdgeGlowTop", "mEdgeGlowBottom", "mEdgeGlowLeft", "mEdgeGlowRight"};
        for (String edgeGlow : edgeGlows) {
            Class<?> clazz = scrollableView.getClass();
            while (clazz != null) {
                try {
                    final Field edgeGlowField = clazz.getDeclaredField(edgeGlow);
                    edgeGlowField.setAccessible(true);
                    final EdgeEffect edgeEffect = (EdgeEffect) edgeGlowField.get(scrollableView);
                    edgeEffect.setColor(color);
                    break;
                } catch (Exception e) {
                    clazz = clazz.getSuperclass();
                }
            }
        }
    }
}
