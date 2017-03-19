package com.foxfinity.merlin.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.foxfinity.merlin.R;
import com.foxfinity.merlin.utils.TypefaceHelper;

/**
 * Project Merlin. Created by Izya Pitersky on 3/19/17.
 */

public class MerlinTextView extends TextView {
    private Integer tf = 0;

    public MerlinTextView(Context context) {
        super(context);
        setTypeface(TypefaceHelper.getTypeface(0));
    }

    public MerlinTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.MerlinTextView, 0, 0);
        tf = a.getInteger(R.styleable.MerlinTextView_typeface, 0);
        a.recycle();
        setTypeface(TypefaceHelper.getTypeface(tf));
    }

    public MerlinTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.MerlinTextView, 0, 0);
        tf = a.getInteger(R.styleable.MerlinTextView_typeface, 0);
        a.recycle();
        setTypeface(TypefaceHelper.getTypeface(tf));
    }

    public MerlinTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.MerlinTextView, 0, 0);
        tf = a.getInteger(R.styleable.MerlinTextView_typeface, 0);
        a.recycle();
        setTypeface(TypefaceHelper.getTypeface(tf));
    }

    public void setCustomTypeFace(Integer choiceMode) {
        setTypeface(TypefaceHelper.getTypeface(choiceMode));
    }
}
