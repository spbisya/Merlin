package com.foxfinity.merlin;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.foxfinity.merlin.search.SearchActivity;
import com.foxfinity.merlin.utils.HelperPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project Merlin. Created by Izya Pitersky on 3/19/17.
 */

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.word)
    EditText word;
    @BindView(R.id.appname)
    TextView appname;
    @BindView(R.id.cover)
    View cover;
    @BindView(R.id.change)
    TextView change;
    @BindView(R.id.help)
    TextView help;
    @BindView(R.id.about)
    TextView about;
    @BindView(R.id.themeContainer)
    LinearLayout themeContainer;
    @BindView(R.id.white)
    ImageView white;
    @BindView(R.id.black)
    ImageView black;
    @BindView(R.id.dim_container)
    RelativeLayout dimContainer;
    @BindView(R.id.view)
    View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTheme(HelperPreferences.isWhiteTheme(this) ? R.style.AppThemeWhite : R.style.AppThemeBlack);
        if (getResources().getBoolean(R.bool.portrait_only)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        view.setBackgroundColor(HelperPreferences.isWhiteTheme(this) ? Color.BLACK : Color.WHITE);
        cover.setOnClickListener(v -> SearchActivity.display(this, appname, word));
        change.setOnClickListener(v -> openChangeDialog());
        dimContainer.setOnClickListener(v -> {
            dimContainer.setVisibility(View.GONE);
            themeContainer.setVisibility(View.GONE);
            setButton(false, change);
        });
        about.setOnClickListener(v -> {
            finish();
            startActivity(new Intent(this, AboutActivity.class));
        });
        help.setOnClickListener(v->{
            finish();
            startActivity(new Intent(this, HelpActivity.class));
        });
    }

    public void setButton(Boolean isPressed, TextView tv) {
        if (isPressed) {
            tv.setBackground(getResources().getDrawable(R.drawable.tv_bg_pressed));
            tv.setTextColor(Color.WHITE);
        } else {
            tv.setBackground(getResources().getDrawable(R.drawable.button_bg));
            tv.setTextColor(Color.parseColor("#0072ff"));
        }
    }

    public void openChangeDialog() {
        dimContainer.setVisibility(View.VISIBLE);
        themeContainer.setVisibility(View.VISIBLE);
        Boolean isWhiteTheme = HelperPreferences.isWhiteTheme(this);
        setButton(true, change);
        white.setImageResource(isWhiteTheme ? R.drawable.won : R.drawable.woff);
        black.setImageResource(isWhiteTheme ? R.drawable.boff : R.drawable.bon);
        black.setOnClickListener(v -> {
            if (isWhiteTheme) {
                HelperPreferences.changeTheme(this, false);
                recreate();
            }
            setButton(false, change);
            themeContainer.setVisibility(View.GONE);
            dimContainer.setVisibility(View.GONE);
        });
        white.setOnClickListener(v -> {
            if (!isWhiteTheme) {
                HelperPreferences.changeTheme(this, true);
                recreate();
            }
            setButton(false, change);
            themeContainer.setVisibility(View.GONE);
            dimContainer.setVisibility(View.GONE);
        });
    }

}
