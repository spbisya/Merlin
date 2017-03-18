package com.foxfinity.merlin.search;

import android.support.annotation.NonNull;
import android.util.Log;

import com.foxfinity.merlin.models.Word;
import com.foxfinity.merlin.network.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;


/**
 * Project Merlin. Created by Izya Pitersky on 3/18/17.
 */

public class SearchPresenter implements SearchContract.Presenter {
    private final SearchContract.View mSearchView;

    public SearchPresenter(@NonNull SearchContract.View searchView) {
        mSearchView = checkNotNull(searchView);
        mSearchView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void searchExpression(String expression) {
        Api.getService().getWordsByMask(expression).enqueue(new Callback<List<Word>>() {
            @Override
            public void onResponse(Call<List<Word>> call, Response<List<Word>> response) {
                if (response.isSuccessful()) {
                    mSearchView.showWords(response.body());
                } else {
                    mSearchView.showNoWords();
                }
            }

            @Override
            public void onFailure(Call<List<Word>> call, Throwable t) {
                mSearchView.showNetworkProblemToast();
            }
        });
    }
}
