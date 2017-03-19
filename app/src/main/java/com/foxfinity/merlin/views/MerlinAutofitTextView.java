package com.foxfinity.merlin.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.foxfinity.merlin.R;
import com.foxfinity.merlin.utils.TypefaceHelper;

import me.grantland.widget.AutofitTextView;

/**
 * Project Merlin. Created by Izya Pitersky on 3/19/17.
 */

public class MerlinAutofitTextView extends AutofitTextView {
    private Integer tf = 0;

    public MerlinAutofitTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.MerlinTextView, 0, 0);
        tf = a.getInteger(R.styleable.MerlinTextView_typeface, 0);
        a.recycle();
        setTypeface(TypefaceHelper.getTypeface(tf));
    }

    public MerlinAutofitTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.MerlinTextView, 0, 0);
        tf = a.getInteger(R.styleable.MerlinTextView_typeface, 0);
        a.recycle();
        setTypeface(TypefaceHelper.getTypeface(tf));
    }
}
