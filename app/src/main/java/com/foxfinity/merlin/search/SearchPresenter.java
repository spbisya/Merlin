package com.foxfinity.merlin.search;

import android.os.Handler;
import android.text.Editable;

import com.foxfinity.merlin.base.Presenter;
import com.foxfinity.merlin.models.Word;
import com.foxfinity.merlin.network.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Project Merlin. Created by Izya Pitersky on 3/18/17.
 */

public class SearchPresenter extends Presenter<SearchContract.ISearchView> implements SearchContract.ISearchPresenter {
    private Handler handler;

    SearchPresenter() {
        handler = new Handler();
    }

    @Override
    public void searchExpression(String expression) {
        Api.getService().getWordsByMask(expression).enqueue(new Callback<List<Word>>() {
            @Override
            public void onResponse(Call<List<Word>> call, Response<List<Word>> response) {
                if (response.isSuccessful()) {
                    getView().showWords(response.body());
                } else {
                    getView().showNoWords();
                }
            }

            @Override
            public void onFailure(Call<List<Word>> call, Throwable t) {
                getView().showNetworkProblemToast();
            }
        });
    }

    @Override
    public void checkEnteredSymbols(Editable s) {
        if (s.length() > 0) {
            handler.removeCallbacksAndMessages(null);
            handler.postDelayed(() -> searchExpression(s.toString()), 300);
        } else getView().showNoWords();
    }

    @Override
    protected void onViewAttached() {

    }

    @Override
    protected void onViewDetached() {

    }
}
