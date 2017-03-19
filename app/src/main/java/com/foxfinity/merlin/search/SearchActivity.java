package com.foxfinity.merlin.search;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.foxfinity.merlin.R;
import com.foxfinity.merlin.adapters.WordAdapter;
import com.foxfinity.merlin.models.Word;
import com.foxfinity.merlin.utils.HelperPreferences;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class SearchActivity extends AppCompatActivity implements SearchContract.ISearchView {
    @BindView(R.id.queryField)
    EditText mQueryField;
    @BindView(R.id.wordList)
    RecyclerView mRecyclerView;
    @BindView(R.id.clear)
    ImageView clear;
    @BindView(R.id.view)
    View v;

    private SearchContract.ISearchPresenter mSearchPresenter;
    private WordAdapter adapter;

    public static void display(Activity activity, View title, View input) {
        Intent intent = new Intent(activity, SearchActivity.class);
        Pair<View, String> p1 = Pair.create(title, "title");
        Pair<View, String> p2 = Pair.create(input, "input");
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, p1, p2);
        activity.startActivity(intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getBoolean(R.bool.portrait_only)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.setTheme(HelperPreferences.isWhiteTheme(this) ? R.style.AppThemeWhite : R.style.AppThemeBlack);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        v.setBackgroundColor(HelperPreferences.isWhiteTheme(this) ? Color.BLACK : Color.WHITE);
        mQueryField.setTextColor(HelperPreferences.isWhiteTheme(this) ? Color.BLACK : Color.WHITE);
        mSearchPresenter = new SearchPresenter();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        adapter = new WordAdapter(this, new ArrayList<>());
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setAdapter(adapter);
        mQueryField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mSearchPresenter.checkEnteredSymbols(s);
            }
        });
        clear.setOnClickListener(v -> supportFinishAfterTransition());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSearchPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSearchPresenter.detachView();
    }

    @Override
    public void showWords(List<Word> words) {
        if (checkNotNull(words).size() > 0) {
            adapter.changeWords(words);
        } else {
            showNoWords();
        }
    }

    @Override
    public void showNoWords() {
        adapter.clearList();
    }

    @Override
    public void showNetworkProblemToast() {
        Toast.makeText(this, "Во время запроса возникла проблема!", Toast.LENGTH_SHORT).show();
    }
}
