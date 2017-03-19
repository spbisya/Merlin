package com.foxfinity.merlin;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.foxfinity.merlin.utils.HelperPreferences;

import java.util.List;
import java.util.Stack;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project Merlin. Created by Izya Pitersky on 3/20/17.
 */

public class AboutActivity extends AppCompatActivity {
    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.write)
    TextView write;
    @BindView(R.id.visit)
    TextView visit;

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
    @BindView(R.id.image)
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getBoolean(R.bool.portrait_only)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.setTheme(HelperPreferences.isWhiteTheme(this) ? R.style.AppThemeWhite : R.style.AppThemeBlack);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        logo.setImageResource(HelperPreferences.isWhiteTheme(this) ? R.drawable.logo : R.drawable.logo_black);
        close.setOnClickListener(v -> {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        });
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("*/*");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"foxfinity.studio@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Question about Merlin app");
                i.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(createEmailOnlyChooserIntent(i));
            }
        });
        visit.setOnClickListener(v -> {
            String url = "http://www.foxfinity.tk";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });
        dimContainer.setOnClickListener(v -> {
            dimContainer.setVisibility(View.GONE);
            themeContainer.setVisibility(View.GONE);
            setButton(false, change);
            setButton(true, about);
        });
        change.setOnClickListener(v -> openChangeDialog());
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
        setButton(false, about);
        white.setImageResource(isWhiteTheme ? R.drawable.won : R.drawable.woff);
        black.setImageResource(isWhiteTheme ? R.drawable.boff : R.drawable.bon);
        black.setOnClickListener(v -> {
            if (isWhiteTheme) {
                HelperPreferences.changeTheme(this, false);
                recreate();
            }
            setButton(true, about);
            setButton(false, change);
            themeContainer.setVisibility(View.GONE);
            dimContainer.setVisibility(View.GONE);
        });
        white.setOnClickListener(v -> {
            if (!isWhiteTheme) {
                HelperPreferences.changeTheme(this, true);
                recreate();
            }
            setButton(true, about);
            setButton(false, change);
            themeContainer.setVisibility(View.GONE);
            dimContainer.setVisibility(View.GONE);
        });
    }

    public Intent createEmailOnlyChooserIntent(Intent source) {
        Stack<Intent> intents = new Stack<Intent>();
        Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",
                "foxfinity.studio@gmail.com", null));
        List<ResolveInfo> activities = getPackageManager()
                .queryIntentActivities(i, 0);

        for (ResolveInfo ri : activities) {
            Intent target = new Intent(source);
            target.setPackage(ri.activityInfo.packageName);
            intents.add(target);
        }

        if (!intents.isEmpty()) {
            Intent chooserIntent = Intent.createChooser(intents.remove(0),
                    "Send email");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
                    intents.toArray(new Parcelable[intents.size()]));

            return chooserIntent;
        } else {
            return Intent.createChooser(source, "Send email");
        }
    }
}
