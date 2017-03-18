package com.foxfinity.merlin.search;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.foxfinity.merlin.R;
import com.foxfinity.merlin.adapters.WordAdapter;
import com.foxfinity.merlin.models.Word;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {
    @BindView(R.id.queryField)
    EditText mQueryField;
    @BindView(R.id.wordList)
    RecyclerView mRecyclerView;
    @BindView(R.id.holder)
    TextView mHolder;

    private SearchContract.Presenter mPresenter;
    private WordAdapter adapter;
    private List<Word> words;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new SearchPresenter(this);
        handler = new Handler();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        words = new ArrayList<>();
        adapter = new WordAdapter(words);
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
                if (s.length() > 0) {
                    handler.removeCallbacksAndMessages(null);
                    handler.postDelayed(() -> mPresenter.searchExpression(s.toString()), 300);
                } else showNoWords();
            }
        });
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showWords(List<Word> words) {
        if (checkNotNull(words).size() > 0) {
            this.words.clear();
            this.words.addAll(words);
            adapter.notifyDataSetChanged();
            if (mHolder.getVisibility() == View.VISIBLE)
                mHolder.setVisibility(View.GONE);
        } else {
            showNoWords();
        }
    }

    @Override
    public void showNoWords() {
        words.clear();
        adapter.notifyDataSetChanged();
        mHolder.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNetworkProblemToast() {
        Toast.makeText(this, "Во время запроса возникла проблема!", Toast.LENGTH_SHORT).show();
    }
}
