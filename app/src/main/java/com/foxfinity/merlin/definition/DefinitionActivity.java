package com.foxfinity.merlin.definition;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.foxfinity.merlin.R;
import com.foxfinity.merlin.utils.HelperPreferences;
import com.foxfinity.merlin.views.DefinitionBlockView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project Merlin. Created by Izya Pitersky on 3/19/17.
 */

public class DefinitionActivity extends AppCompatActivity implements DefinitionContract.IDefinitionView {
    @BindView(R.id.close)
    View close;
    @BindView(R.id.wordView)
    TextView wordView;
    @BindView(R.id.definitions)
    LinearLayout definitionsLayout;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.scroll_view)
    ScrollView scrollView;

    private DefinitionContract.IDefinitionPresenter mDefinitionPresenter;

    public static void display(Context context, String word) {
        Intent intent = new Intent(context, DefinitionActivity.class);
        intent.putExtra("word", word);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getBoolean(R.bool.portrait_only)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.setTheme(HelperPreferences.isWhiteTheme(this) ? R.style.AppThemeWhite : R.style.AppThemeBlack);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_definition);
        ButterKnife.bind(this);
        close.setOnClickListener(v -> finish());
        String word = getIntent().getStringExtra("word");
        wordView.setText(word);
        mDefinitionPresenter = new DefinitionPresenter(word);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDefinitionPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDefinitionPresenter.detachView();
    }

    @Override
    public void showNetworkError() {
        Toast.makeText(this, "Произошла ошибка, попробуйте повторить запрос!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startRefreshing() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopRefreshing() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showDefinitionAnswer(HashMap<String, ArrayList<String>> sortedDefinitions) {
        for (String key : sortedDefinitions.keySet()) {
            definitionsLayout.addView(new DefinitionBlockView(this).withDefinitions(key, sortedDefinitions.get(key)));
        }
    }
}
