package com.foxfinity.merlin.search;

import android.text.Editable;

import com.foxfinity.merlin.base.IPresenter;
import com.foxfinity.merlin.base.IView;
import com.foxfinity.merlin.models.Word;

import java.util.List;

/**
 * Project Merlin. Created by Izya Pitersky on 3/18/17.
 * This specifies the contract between the view and the presenter.
 */

public interface SearchContract {
    interface ISearchView extends IView<ISearchPresenter> {
        void showWords(List<Word> words);

        void showNoWords();

        void showNetworkProblemToast();
    }

    interface ISearchPresenter extends IPresenter<ISearchView>{
        void searchExpression(String expression);

        void checkEnteredSymbols(Editable s);
    }
}
