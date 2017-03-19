package com.foxfinity.merlin.definition;

import com.foxfinity.merlin.base.Presenter;
import com.foxfinity.merlin.models.DefinitionAnswer;
import com.foxfinity.merlin.network.Api;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Project Merlin. Created by Izya Pitersky on 3/19/17.
 */

public class DefinitionPresenter extends Presenter<DefinitionContract.IDefinitionView> implements DefinitionContract.IDefinitionPresenter {
    private String word;

    DefinitionPresenter(String word) {
        this.word = word;
    }

    @Override
    public void searchDefinition(String word) {
        getView().startRefreshing();
        Api.getDefinitionsService().getDefinitions(word).enqueue(new Callback<DefinitionAnswer>() {
            @Override
            public void onResponse(Call<DefinitionAnswer> call, Response<DefinitionAnswer> response) {
                if (response.isSuccessful()) {
                    HashMap<String, ArrayList<String>> sortedDefinitions = new HashMap<>();
                    for (DefinitionAnswer.Definition definition : response.body().getDefinitions()) {
                        ArrayList<String> definitionsList = sortedDefinitions.containsKey(definition.getPartOfSpeech())
                                ? sortedDefinitions.get(definition.getPartOfSpeech())
                                : new ArrayList<>();
                        definitionsList.add(definition.getDefinition());
                        sortedDefinitions.put(definition.getPartOfSpeech(), definitionsList);
                    }
                    getView().showDefinitionAnswer(sortedDefinitions);
                } else {
                    getView().showNetworkError();
                }
                getView().stopRefreshing();
            }

            @Override
            public void onFailure(Call<DefinitionAnswer> call, Throwable t) {
                getView().showNetworkError();
                getView().stopRefreshing();
            }
        });
    }

    @Override
    protected void onViewAttached() {
        searchDefinition(word);
    }

    @Override
    protected void onViewDetached() {

    }
}
