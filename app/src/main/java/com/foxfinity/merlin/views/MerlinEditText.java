package com.foxfinity.merlin.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;

import com.foxfinity.merlin.R;
import com.foxfinity.merlin.utils.TypefaceHelper;

/**
 * Project Merlin. Created by Izya Pitersky on 3/19/17.
 */

public class MerlinEditText extends EditText {
    private Integer tf = 0;

    public MerlinEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.MerlinTextView, 0, 0);
        tf = a.getInteger(R.styleable.MerlinTextView_typeface, 0);
        a.recycle();
        setTypeface(TypefaceHelper.getTypeface(tf));
    }

    public MerlinEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.MerlinTextView, 0, 0);
        tf = a.getInteger(R.styleable.MerlinTextView_typeface, 0);
        a.recycle();
        setTypeface(TypefaceHelper.getTypeface(tf));
    }

    public MerlinEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
